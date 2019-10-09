package Display;

import Display.Model.PaintUtil;
import Display.Model.Observer;
import Display.Model.Subject;
import Display.Serial.SerialPortManager;
import Display.utils.*;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
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
import Display.Model.Point;

public class Painter extends JPanel implements Subject {
    //观察者
    private List<Observer> observers = new ArrayList<Observer>();
    //位置数据
    private float x;
    private float y;
    //位移数据显示位置
    private int xPlot_x;
    private int xPlot_y;
    private int yPlot_x;
    private int yPlot_y;
    //校准
    private float markX;
    private float markY;

    //角度数据
    private float degree_roll;//x
    private float degree_pit;//y
    private int lineLength;
    private Random rand = new Random();

    private JPanel configPanel;
    private JLabel mSerialPortLabel;
    private JLabel mBaudrateLabel;
    private JComboBox mCommChoice;
    private JComboBox mBaudrateChoice;

    /*private ButtonGroup mDataChoice;
    private JRadioButton mDataASCIIChoice;
    private JRadioButton mDataHexChoice;*/

    private JButton openSerialBtn;
    private JScrollPane dataPane;

    private int xTrans;
    private int yTrans;
    private int scaleX = 1;
    private int scaleY = 1;

    private ArrayList<Point> queue_move;
    private static final int queueLength = 20;

    public static final float RopeLength = 1.5f;
    private boolean isFirstReadAngle = true;

    private float mFirstPit = 0;
    private float mFirstRoll = 0;

    // 速度值
    private float xSpeed = 0;
    private float ySpeed = 0;
    private float lSpeed = 0;

    // 串口列表
    private List<String> mCommList = null;
    // 串口对象
    private SerialPort mSerialport;

    // Threshold parameters
    private static final float CalibrationAngle = 3.0f;

    //构造器
    Painter(){
        super();
        this.setLayout(null);//不用布局

        queue_move = new ArrayList<Point>();
        xTrans = PaintUtil.xTrans;
        yTrans = PaintUtil.yTrans;
        initComponent();
        addListeners();
    }

    /*
    * 初始化静态组件
    * */
    private void initComponent(){
        //二维投影平面图
        JLabel title_2D = new JLabel("二维投影平面图");
        title_2D.setBorder(BorderFactory.createRaisedBevelBorder());
        title_2D.setBounds(0,0,135*xTrans,30*yTrans);
        title_2D.setFont(PaintUtil.titleFont);
        this.add(title_2D);

        //摆动效果图
        JLabel title_swing = new JLabel("摆动效果图");
        title_swing.setBorder(BorderFactory.createRaisedBevelBorder());
        title_swing.setFont(PaintUtil.titleFont);
        title_swing.setBounds(PaintUtil.originX + PaintUtil.axisWidth + 15*xTrans,0,100*xTrans,30*yTrans);
        this.add(title_swing);

        //速度数据显示
        JPanel move_speed_panel = new JPanel();
        move_speed_panel.setBorder(BorderFactory.createTitledBorder("速度"));
        move_speed_panel.setFont(PaintUtil.subTitleFont);
        move_speed_panel.setBounds(PaintUtil.originX + 200*PaintUtil.xTrans
                , PaintUtil.originY + 280 * PaintUtil.yTrans
                , 100 * PaintUtil.xTrans, 40 * PaintUtil.yTrans);
        move_speed_panel.setLayout(null);
        this.add(move_speed_panel);

        //位移数据显示
        JPanel move_data_panel = new JPanel();
        move_data_panel.setBorder(BorderFactory.createTitledBorder("位移数据"));
        move_data_panel.setFont(PaintUtil.subTitleFont);
        move_data_panel.setBounds(PaintUtil.originX + 180*PaintUtil.xTrans
                , PaintUtil.originY + 320 * PaintUtil.yTrans
                , 150 * PaintUtil.xTrans, 70 * PaintUtil.yTrans);
        move_data_panel.setLayout(null);
        this.add(move_data_panel);

        //角度数据显示
        JPanel degree_data_panel = new JPanel();
        degree_data_panel.setBorder(BorderFactory.createTitledBorder("偏转角"));
        degree_data_panel.setFont(PaintUtil.subTitleFont);
        degree_data_panel.setBounds(PaintUtil.originX + 900*PaintUtil.xTrans
                , 20 * PaintUtil.yTrans
                , 100 * PaintUtil.xTrans, 40 * PaintUtil.yTrans);
        degree_data_panel.setLayout(null);
        this.add(degree_data_panel);

        //x label
        JLabel xLabel = new JLabel("x:");
        xLabel.setBounds(25*xTrans,20*yTrans,20*xTrans,20*yTrans);
        xLabel.setFont(PaintUtil.boldFont);
        move_data_panel.add(xLabel);

        //y label
        JLabel yLabel = new JLabel("y:");
        yLabel.setBounds(25*xTrans,40*yTrans,20*xTrans,20*yTrans);
        yLabel.setFont(PaintUtil.boldFont);
        move_data_panel.add(yLabel);
        yPlot_x = 25*xTrans;
        yPlot_y = 40*yTrans;


        //设置板块
        configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder("串口设置"));
        configPanel.setBounds(PaintUtil.originX_side + 230*PaintUtil.xTrans
                                , PaintUtil.originY_side + 250 * PaintUtil.yTrans
                                , 200 * PaintUtil.xTrans, 150 * PaintUtil.yTrans);
        configPanel.setLayout(null);
        this.add(configPanel);

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


        //动态3D图



        /*
        * 立体翻滚图
        * */
        //板块
        JPanel roll_View = new JPanel();
        roll_View.setBorder(BorderFactory.createTitledBorder("转动图"));
        roll_View.setBounds(800,400,300,300);
        this.add(roll_View);
        //JOGL动态图
        final GLProfile profile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities capabilities = new GLCapabilities( profile );
        final GLCanvas glcanvas = new GLCanvas( capabilities );// The canvas
        Cube cube = new Cube();
        registerObserver(cube);
        glcanvas.addGLEventListener(cube);
        glcanvas.setSize( 200, 200 );
        roll_View.add( glcanvas );
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
        animator.start();


        //打开串口的按钮
        openSerialBtn = new JButton("打开串口");
        openSerialBtn.setBounds(60*xTrans,110*yTrans,100*xTrans,30*yTrans);
        openSerialBtn.setFont(PaintUtil.boldFont);
        configPanel.add(openSerialBtn);
        openSerialBtn.setBorder(BorderFactory.createRaisedBevelBorder());
    }


    /*
    * 动态画图区
    * */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(PaintUtil.basicFont);

        // code for calibration
//        if (Math.abs(degree_roll) < CalibrationAngle){
//            markY = y;
//        }
//        if (Math.abs(degree_pit) < CalibrationAngle){
//            markX = x;
//        }

        //俯视图
        PaintUtil.drawAxis(g2d,scaleX,scaleY);
        PaintUtil.drawDynamicAll(g2d,new ArrayList<Point>(queue_move));
        //PaintUtil.drawDynamic(g2d,-(y-markY),x-markX);

        //分界线
        PaintUtil.drawInterLine(g2d);

        //侧视图
        PaintUtil.drawAxisSideView(g2d);
        PaintUtil.drawDynamicSide(g2d,degree_roll);

        //当前位移数据显示
        PaintUtil.drawMoveData(g2d,575*xTrans,705*yTrans,575*xTrans,725*yTrans,x,y);

        //速度显示
        PaintUtil.drawSpeedData(g2d,lSpeed,570*xTrans,660*yTrans);

        //二维偏转角显示
        PaintUtil.drawRollDegree(g2d,degree_roll,1280*xTrans,50*yTrans);

        repaint();
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
//        mBaudrateChoice.addItem("9600");
//        mBaudrateChoice.addItem("19200");
//        mBaudrateChoice.addItem("38400");
//        mBaudrateChoice.addItem("57600");
//        mBaudrateChoice.addItem("115200");
//        mBaudrateChoice.addItem("256000");
        mBaudrateChoice.addItem("500000");

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
            ShowUtils.warningMessage("没有搜索到有效串口!");
        } else {
            try {
                mSerialport = SerialPortManager.openPort(commName, baudrate);
                if (mSerialport != null) {
                    openSerialBtn.setText("关闭串口");
                }
            } catch (PortInUseException e) {
                ShowUtils.warningMessage("串口已被占用！");
            }
        }

        // 添加串口监听
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {
            public void dataAvailable() {
                byte[] first = null;
                int i = 0;
                try {
                    if (mSerialport == null) {
                        ShowUtils.errorMessage("串口对象为空，监听失败！");
                    } else {
                        //读取串口数据
                        List<Integer> list = SerialPortManager.readBytesFromPort(mSerialport);
                        if (list.size() < 3){
                            return;
                        }

                        if (list.get(0) == 1){//位移数据
                            List<Float> movement = ByteUtils.bytesToFloat(list);
                            if (movement.size() >= 2){
//                                x = movement.get(0) / 10;
//                                y = movement.get(1) / 10;
                            }
                            if (queue_move.size() == queueLength ){
                                queue_move.remove(0);
                            }
                            queue_move.add(new Point(-(y),x));
                            xSpeed = movement.get(5) / 100;
                            ySpeed = movement.get(4) / 100;
                            lSpeed = (float) Math.sqrt(Math.pow(xSpeed,2)+Math.pow(ySpeed,2));
                        }else {//来自串口的角度数据
                            List<Float> degreeList = ByteUtils.bytesToFloat(list);
                            if (isFirstReadAngle){
                                isFirstReadAngle = false;
                                mFirstRoll = degreeList.get(0);
                                mFirstPit = degreeList.get(1);
                            }
                            //校准后的角度
                            degree_roll = degreeList.get(0) - mFirstRoll;
                            degree_pit = degreeList.get(1) - mFirstPit;
                            notifyObservers(degree_roll,degree_pit);
                            y = RopeLength * (float) Math.sin(degree_roll * Math.PI / 180.0f) * 2;
                            x = RopeLength * (float) Math.sin(degree_pit * Math.PI / 180.0f) * 2;

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //ShowUtils.errorMessage(e.toString());
                    // 发生读取错误时显示错误信息后退出系统
                    System.exit(0);
                }
            }
        });
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers1(String s) {

    }

    public void notifyObservers2(float roll_degree, float pit_degree) {
        for (Observer ob : observers){
            ob.update2(roll_degree,pit_degree);
        }
    }

    public void notifyObservers(float roll_degree,float pit_degree) {
        for (Observer ob : observers){
            ob.update2(roll_degree,pit_degree);
        }
    }
}
