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
    //�۲���
    private List<Observer> observers = new ArrayList<Observer>();
    //λ������
    private float x;
    private float y;
    //λ��������ʾλ��
    private int xPlot_x;
    private int xPlot_y;
    private int yPlot_x;
    private int yPlot_y;
    //У׼
    private float markX;
    private float markY;

    //�Ƕ�����
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

    // �ٶ�ֵ
    private float xSpeed = 0;
    private float ySpeed = 0;
    private float lSpeed = 0;

    // �����б�
    private List<String> mCommList = null;
    // ���ڶ���
    private SerialPort mSerialport;

    // Threshold parameters
    private static final float CalibrationAngle = 3.0f;

    //������
    Painter(){
        super();
        this.setLayout(null);//���ò���

        queue_move = new ArrayList<Point>();
        xTrans = PaintUtil.xTrans;
        yTrans = PaintUtil.yTrans;
        initComponent();
        addListeners();
    }

    /*
    * ��ʼ����̬���
    * */
    private void initComponent(){
        //��άͶӰƽ��ͼ
        JLabel title_2D = new JLabel("��άͶӰƽ��ͼ");
        title_2D.setBorder(BorderFactory.createRaisedBevelBorder());
        title_2D.setBounds(0,0,135*xTrans,30*yTrans);
        title_2D.setFont(PaintUtil.titleFont);
        this.add(title_2D);

        //�ڶ�Ч��ͼ
        JLabel title_swing = new JLabel("�ڶ�Ч��ͼ");
        title_swing.setBorder(BorderFactory.createRaisedBevelBorder());
        title_swing.setFont(PaintUtil.titleFont);
        title_swing.setBounds(PaintUtil.originX + PaintUtil.axisWidth + 15*xTrans,0,100*xTrans,30*yTrans);
        this.add(title_swing);

        //�ٶ�������ʾ
        JPanel move_speed_panel = new JPanel();
        move_speed_panel.setBorder(BorderFactory.createTitledBorder("�ٶ�"));
        move_speed_panel.setFont(PaintUtil.subTitleFont);
        move_speed_panel.setBounds(PaintUtil.originX + 200*PaintUtil.xTrans
                , PaintUtil.originY + 280 * PaintUtil.yTrans
                , 100 * PaintUtil.xTrans, 40 * PaintUtil.yTrans);
        move_speed_panel.setLayout(null);
        this.add(move_speed_panel);

        //λ��������ʾ
        JPanel move_data_panel = new JPanel();
        move_data_panel.setBorder(BorderFactory.createTitledBorder("λ������"));
        move_data_panel.setFont(PaintUtil.subTitleFont);
        move_data_panel.setBounds(PaintUtil.originX + 180*PaintUtil.xTrans
                , PaintUtil.originY + 320 * PaintUtil.yTrans
                , 150 * PaintUtil.xTrans, 70 * PaintUtil.yTrans);
        move_data_panel.setLayout(null);
        this.add(move_data_panel);

        //�Ƕ�������ʾ
        JPanel degree_data_panel = new JPanel();
        degree_data_panel.setBorder(BorderFactory.createTitledBorder("ƫת��"));
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


        //���ð��
        configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder("��������"));
        configPanel.setBounds(PaintUtil.originX_side + 230*PaintUtil.xTrans
                                , PaintUtil.originY_side + 250 * PaintUtil.yTrans
                                , 200 * PaintUtil.xTrans, 150 * PaintUtil.yTrans);
        configPanel.setLayout(null);
        this.add(configPanel);

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


        //��̬3Dͼ



        /*
        * ���巭��ͼ
        * */
        //���
        JPanel roll_View = new JPanel();
        roll_View.setBorder(BorderFactory.createTitledBorder("ת��ͼ"));
        roll_View.setBounds(800,400,300,300);
        this.add(roll_View);
        //JOGL��̬ͼ
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


        //�򿪴��ڵİ�ť
        openSerialBtn = new JButton("�򿪴���");
        openSerialBtn.setBounds(60*xTrans,110*yTrans,100*xTrans,30*yTrans);
        openSerialBtn.setFont(PaintUtil.boldFont);
        configPanel.add(openSerialBtn);
        openSerialBtn.setBorder(BorderFactory.createRaisedBevelBorder());
    }


    /*
    * ��̬��ͼ��
    * */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //�����
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(PaintUtil.basicFont);

        // code for calibration
//        if (Math.abs(degree_roll) < CalibrationAngle){
//            markY = y;
//        }
//        if (Math.abs(degree_pit) < CalibrationAngle){
//            markX = x;
//        }

        //����ͼ
        PaintUtil.drawAxis(g2d,scaleX,scaleY);
        PaintUtil.drawDynamicAll(g2d,new ArrayList<Point>(queue_move));
        //PaintUtil.drawDynamic(g2d,-(y-markY),x-markX);

        //�ֽ���
        PaintUtil.drawInterLine(g2d);

        //����ͼ
        PaintUtil.drawAxisSideView(g2d);
        PaintUtil.drawDynamicSide(g2d,degree_roll);

        //��ǰλ��������ʾ
        PaintUtil.drawMoveData(g2d,575*xTrans,705*yTrans,575*xTrans,725*yTrans,x,y);

        //�ٶ���ʾ
        PaintUtil.drawSpeedData(g2d,lSpeed,570*xTrans,660*yTrans);

        //��άƫת����ʾ
        PaintUtil.drawRollDegree(g2d,degree_roll,1280*xTrans,50*yTrans);

        repaint();
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
            ShowUtils.warningMessage("û����������Ч����!");
        } else {
            try {
                mSerialport = SerialPortManager.openPort(commName, baudrate);
                if (mSerialport != null) {
                    openSerialBtn.setText("�رմ���");
                }
            } catch (PortInUseException e) {
                ShowUtils.warningMessage("�����ѱ�ռ�ã�");
            }
        }

        // ��Ӵ��ڼ���
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {
            public void dataAvailable() {
                byte[] first = null;
                int i = 0;
                try {
                    if (mSerialport == null) {
                        ShowUtils.errorMessage("���ڶ���Ϊ�գ�����ʧ�ܣ�");
                    } else {
                        //��ȡ��������
                        List<Integer> list = SerialPortManager.readBytesFromPort(mSerialport);
                        if (list.size() < 3){
                            return;
                        }

                        if (list.get(0) == 1){//λ������
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
                        }else {//���Դ��ڵĽǶ�����
                            List<Float> degreeList = ByteUtils.bytesToFloat(list);
                            if (isFirstReadAngle){
                                isFirstReadAngle = false;
                                mFirstRoll = degreeList.get(0);
                                mFirstPit = degreeList.get(1);
                            }
                            //У׼��ĽǶ�
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
                    // ������ȡ����ʱ��ʾ������Ϣ���˳�ϵͳ
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
