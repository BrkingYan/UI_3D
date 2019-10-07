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

    // �����б�
    private List<String> mCommList = null;
    // ���ڶ���
    private SerialPort mSerialport;

    //������
    Painter(){
        super();
        this.setLayout(null);//���ò���
        xTrans = PaintUtil.xTrans;
        yTrans = PaintUtil.yTrans;
        initComponent();
        addListeners();
    }

    /*
    * ��ʼ����̬���
    * */
    private void initComponent(){
        //���ð��
        configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder("��������"));
        configPanel.setBounds(PaintUtil.originX_side - 50*PaintUtil.xTrans
                                , PaintUtil.originY_side + 100 * PaintUtil.yTrans
                                , 200 * PaintUtil.xTrans, 200 * PaintUtil.yTrans);
        configPanel.setLayout(null);
        this.add(configPanel);


        //������ʾ��
        mDataView = new JTextArea();
        mDataView.setBounds(PaintUtil.originX_side - 300*PaintUtil.xTrans
                , PaintUtil.originY_side + 100 * PaintUtil.yTrans
                , 200 * PaintUtil.xTrans, 200 * PaintUtil.yTrans);
        mDataView.setBorder(BorderFactory.createTitledBorder("����"));
        this.add(mDataView);

        //���ڱ�ǩ
        mSerialPortLabel = new JLabel("����:");
        mSerialPortLabel.setForeground(Color.gray);
        mSerialPortLabel.setBounds(10*xTrans,22*yTrans,60*xTrans,20*yTrans);
        configPanel.add(mSerialPortLabel);
        //�����ʱ�ǩ
        mBaudrateLabel = new JLabel("������:");
        mBaudrateLabel.setForeground(Color.gray);
        mBaudrateLabel.setBounds(10*xTrans,62*yTrans,60*xTrans,20*yTrans);
        configPanel.add(mBaudrateLabel);
        //����������
        mCommChoice = new JComboBox();
        mCommChoice.setForeground(Color.gray);
        mCommChoice.setBounds(80*xTrans,22*yTrans,90*xTrans,20*yTrans);
        configPanel.add(mCommChoice);
        //������������
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


        //�򿪴��ڵİ�ť
        openSerialBtn = new JButton("�򿪴���");
        openSerialBtn.setBounds(60*xTrans,130*yTrans,60*xTrans,40*yTrans);
        configPanel.add(openSerialBtn);
        openSerialBtn.setBorder(BorderFactory.createRaisedBevelBorder());

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //�����
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //����ͼ
        PaintUtil.drawAxis(g2d);
        PaintUtil.drawDynamic(g2d,x,y);

        //�ֽ���
        PaintUtil.drawInterLine(g2d);

        //����ͼ
        PaintUtil.drawAxisSideView(g2d);
        PaintUtil.drawDynamicSide(g2d,x);

        repaint();
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private void addListeners(){
        //��/�رմ���
        openSerialBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ("�򿪴���".equals(openSerialBtn.getText()) && mSerialport == null) {
                    openSerialPort(e);
                } else {
                    closeSerialPort(e);
                }
            }
        });

        // ����
        mCommList = SerialPortManager.findPorts();
        // ����Ƿ��п��ô��ڣ��������ѡ����
        if (mCommList == null || mCommList.size() < 1) {
            ShowUtils.warningMessage("û����������Ч���ڣ�");
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
                // ����Ƿ��п��ô��ڣ��������ѡ����
                if (mCommList == null || mCommList.size() < 1) {
                    ShowUtils.warningMessage("û����������Ч���ڣ�");
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
     * �رմ���
     *
     * @param evt
     *            ����¼�
     */
    private void closeSerialPort(java.awt.event.ActionEvent evt) {
        SerialPortManager.closePort(mSerialport);
        mDataView.setText("�����ѹر�" + "\r\n");
        openSerialBtn.setText("�򿪴���");
        mSerialport = null;
    }


    /**
     * �򿪴���
     *
     * @param evt
     *            ����¼�
     */
    private void openSerialPort(java.awt.event.ActionEvent evt) {
        // ��ȡ��������
        String commName = (String) mCommChoice.getSelectedItem();
        // ��ȡ�����ʣ�Ĭ��Ϊ9600
        int baudrate = 9600;
        String bps = (String) mBaudrateChoice.getSelectedItem();
        baudrate = Integer.parseInt(bps);

        // ��鴮�������Ƿ��ȡ��ȷ
        if (commName == null || commName.equals("")) {
            ShowUtils.warningMessage("û����������Ч���ڣ�");
        } else {
            try {
                mSerialport = SerialPortManager.openPort(commName, baudrate);
                if (mSerialport != null) {
                    mDataView.setText("�����Ѵ�" + "\r\n");
                    openSerialBtn.setText("�رմ���");
                }
            } catch (PortInUseException e) {
                ShowUtils.warningMessage("�����ѱ�ռ�ã�");
            }
        }

        // ��Ӵ��ڼ���
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {

            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (mSerialport == null) {
                        ShowUtils.errorMessage("���ڶ���Ϊ�գ�����ʧ�ܣ�");
                    } else {
                        // ��ȡ��������
                        data = SerialPortManager.readFromPort(mSerialport);

                        // ���ַ�������ʽ��������
                        if (mDataASCIIChoice.isSelected()) {
                            mDataView.append(new String(data) + "\r\n");
                        }

                        // ��ʮ�����Ƶ���ʽ��������
                        if (mDataHexChoice.isSelected()) {
                            mDataView.append(ByteUtils.byteArrayToHexString(data) + "\r\n");
                        }
                    }
                } catch (Exception e) {
                    ShowUtils.errorMessage(e.toString());
                    // ������ȡ����ʱ��ʾ������Ϣ���˳�ϵͳ
                    System.exit(0);
                }
            }
        });
    }
}
