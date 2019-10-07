package Display;

import Display.Model.PaintUtil;
import Display.Model.Observer;
import Display.Serial.SerialPortManager;
import Display.utils.*;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Painter extends JPanel implements Observer {
    private float x = 1.1f;
    private float y = 0.5f;
    private Random rand = new Random();

    private JPanel configPanel;
    private JLabel mSerialPortLabel;
    private JLabel mBaudrateLabel;
    private JComboBox mCommChoice;
    private JComboBox mBaudrateChoice;
    private ButtonGroup mDataChoice;
    private JRadioButton mDataASCIIChoice;
    private JRadioButton mDataHexChoice;
    private JButton openSerialBtn;
    private JTextArea mDataView;

    private int xTrans;
    private int yTrans;

    // 串口列表
    private List<String> mCommList = null;
    // 串口对象
    private SerialPort mSerialport;

    //构造器
    Painter(){
        super();
        this.setLayout(null);//不用布局
        xTrans = PaintUtil.xTrans;
        yTrans = PaintUtil.yTrans;
        initComponent();
        addListeners();
    }

    /*
    * 初始化静态组件
    * */
    private void initComponent(){
        //设置板块
        configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder("串口设置"));
        configPanel.setBounds(PaintUtil.originX_side - 50*PaintUtil.xTrans
                                , PaintUtil.originY_side + 100 * PaintUtil.yTrans
                                , 200 * PaintUtil.xTrans, 200 * PaintUtil.yTrans);
        configPanel.setLayout(null);
        this.add(configPanel);


        //数据显示区
        mDataView = new JTextArea();
        mDataView.setBounds(PaintUtil.originX_side - 300*PaintUtil.xTrans
                , PaintUtil.originY_side + 100 * PaintUtil.yTrans
                , 200 * PaintUtil.xTrans, 200 * PaintUtil.yTrans);
        mDataView.setBorder(BorderFactory.createTitledBorder("数据"));
        this.add(mDataView);

        //串口标签
        mSerialPortLabel = new JLabel("串口:");
        mSerialPortLabel.setForeground(Color.gray);
        mSerialPortLabel.setBounds(10*xTrans,22*yTrans,60*xTrans,20*yTrans);
        configPanel.add(mSerialPortLabel);
        //波特率标签
        mBaudrateLabel = new JLabel("波特率:");
        mBaudrateLabel.setForeground(Color.gray);
        mBaudrateLabel.setBounds(10*xTrans,62*yTrans,60*xTrans,20*yTrans);
        configPanel.add(mBaudrateLabel);
        //串口下拉栏
        mCommChoice = new JComboBox();
        mCommChoice.setForeground(Color.gray);
        mCommChoice.setBounds(80*xTrans,22*yTrans,90*xTrans,20*yTrans);
        configPanel.add(mCommChoice);
        //波特率下拉栏
        mBaudrateChoice = new JComboBox();
        mBaudrateChoice.setForeground(Color.gray);
        mBaudrateChoice.setBounds(80*xTrans,62*yTrans,90*xTrans,20*yTrans);
        configPanel.add(mBaudrateChoice);

        //ASCII and Hex
        mDataASCIIChoice = new JRadioButton("ASCII", true);
        mDataASCIIChoice.setForeground(Color.gray);
        mDataASCIIChoice.setBounds(10*xTrans,100*yTrans,60*xTrans,20*yTrans);
        configPanel.add(mDataASCIIChoice);

        mDataHexChoice = new JRadioButton("Hex");
        mDataHexChoice.setForeground(Color.gray);
        mDataHexChoice.setBounds(100*xTrans,100*yTrans,60*xTrans,20*yTrans);
        configPanel.add(mDataHexChoice);

        mDataChoice = new ButtonGroup();
        mDataChoice.add(mDataASCIIChoice);
        mDataChoice.add(mDataHexChoice);


        //打开串口的按钮
        openSerialBtn = new JButton("打开串口");
        openSerialBtn.setBounds(60*xTrans,130*yTrans,60*xTrans,40*yTrans);
        configPanel.add(openSerialBtn);
        openSerialBtn.setBorder(BorderFactory.createRaisedBevelBorder());

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //俯视图
        PaintUtil.drawAxis(g2d);
        PaintUtil.drawDynamic(g2d,x,y);

        //分界线
        PaintUtil.drawInterLine(g2d);

        //侧视图
        PaintUtil.drawAxisSideView(g2d);
        PaintUtil.drawDynamicSide(g2d,x);

        repaint();
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private void addListeners(){
        //打开/关闭串口
        openSerialBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("打开串口".equals(openSerialBtn.getText()) && mSerialport == null) {
                    openSerialPort(e);
                } else {
                    closeSerialPort(e);
                }
            }
        });

        // 串口
        mCommList = SerialPortManager.findPorts();
        // 检查是否有可用串口，有则加入选项中
        if (mCommList == null || mCommList.size() < 1) {
            ShowUtils.warningMessage("没有搜索到有效串口！");
        } else {
            for (String s : mCommList) {
                mCommChoice.addItem(s);
            }
        }
        mBaudrateChoice.addItem("9600");
        mBaudrateChoice.addItem("19200");
        mBaudrateChoice.addItem("38400");
        mBaudrateChoice.addItem("57600");
        mBaudrateChoice.addItem("115200");

        mCommChoice.addPopupMenuListener(new PopupMenuListener() {

            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                mCommList = SerialPortManager.findPorts();
                // 检查是否有可用串口，有则加入选项中
                if (mCommList == null || mCommList.size() < 1) {
                    ShowUtils.warningMessage("没有搜索到有效串口！");
                } else {
                    int index = mCommChoice.getSelectedIndex();
                    mCommChoice.removeAllItems();
                    for (String s : mCommList) {
                        mCommChoice.addItem(s);
                    }
                    mCommChoice.setSelectedIndex(index);
                }
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // NO OP
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                // NO OP
            }
        });
    }

    /**
     * 关闭串口
     *
     * @param evt
     *            点击事件
     */
    private void closeSerialPort(java.awt.event.ActionEvent evt) {
        SerialPortManager.closePort(mSerialport);
        mDataView.setText("串口已关闭" + "\r\n");
        openSerialBtn.setText("打开串口");
        mSerialport = null;
    }


    /**
     * 打开串口
     *
     * @param evt
     *            点击事件
     */
    private void openSerialPort(java.awt.event.ActionEvent evt) {
        // 获取串口名称
        String commName = (String) mCommChoice.getSelectedItem();
        // 获取波特率，默认为9600
        int baudrate = 9600;
        String bps = (String) mBaudrateChoice.getSelectedItem();
        baudrate = Integer.parseInt(bps);

        // 检查串口名称是否获取正确
        if (commName == null || commName.equals("")) {
            ShowUtils.warningMessage("没有搜索到有效串口！");
        } else {
            try {
                mSerialport = SerialPortManager.openPort(commName, baudrate);
                if (mSerialport != null) {
                    mDataView.setText("串口已打开" + "\r\n");
                    openSerialBtn.setText("关闭串口");
                }
            } catch (PortInUseException e) {
                ShowUtils.warningMessage("串口已被占用！");
            }
        }

        // 添加串口监听
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {

            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (mSerialport == null) {
                        ShowUtils.errorMessage("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialPortManager.readFromPort(mSerialport);

                        // 以字符串的形式接收数据
                        if (mDataASCIIChoice.isSelected()) {
                            mDataView.append(new String(data) + "\r\n");
                        }

                        // 以十六进制的形式接收数据
                        if (mDataHexChoice.isSelected()) {
                            mDataView.append(ByteUtils.byteArrayToHexString(data) + "\r\n");
                        }
                    }
                } catch (Exception e) {
                    ShowUtils.errorMessage(e.toString());
                    // 发生读取错误时显示错误信息后退出系统
                    System.exit(0);
                }
            }
        });
    }
}
