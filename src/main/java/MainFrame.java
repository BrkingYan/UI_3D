import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//��νṹ  Frame > JPanel > JLabel

/*
* ���ڷ��þ�̬���
* */
public class MainFrame extends JFrame {


    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private float speed = 0;

    public MainFrame(){

        initMainFrame();

        addView(new Painter1(),500,300,300,300);
        addView(new PaintBoard(),100,100,200,200);
    }

    private void addView(Painter painter,int x,int y,int width,int height){
        painter.setBounds(x,y,width,height);
        this.add(painter);
        this.registerObserver(painter);
        new Thread(new Executor(painter)).start();
    }

    private void initMainFrame(){
        //��ʾ��ǰ��Ļ�ֱ���
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


        //��Ӿ�̬�����ͼƬ
        /*JLabel nodeLabel = new JLabel();
        ImageIcon img = new ImageIcon("node.png");// ����ͼƬ����
        nodeLabel.setIcon(img);
        nodeLabel.setBounds(100,100,100,img.getIconHeight());
        nodeLabel.setBackground(Color.BLUE);*/

        //layeredPane.add(nodeLabel,new Integer(Integer.MIN_VALUE));
        //panel.setOpaque(false);


        // ����������
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("GUI");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(1500,800);//��������Ĵ�С
        this.setBounds(0, 0, 1500, 1000);

    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        for (Observer observer : observers){
            if(observer == o){
                observers.remove(observer);
            }
        }
    }

    /*public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(speed);
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        notifyObservers();
    }*/

    /*public void GUI() {
        setTitle("ͼ�����");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("node.png");// ����ͼƬ����
        label.setIcon(img);
        panel.add(label);
        add(panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// JFrame���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ��JFrame�Ĺرհ�ť������
        setVisible(true);// ��ʾJFrame
    }*/
}
