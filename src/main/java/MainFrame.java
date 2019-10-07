import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//层次结构  Frame > JPanel > JLabel

/*
* 用于放置静态组件
* */
public class MainFrame extends JFrame implements Subject {


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
        //显示当前屏幕分辨率
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


        //添加静态组件和图片
        /*JLabel nodeLabel = new JLabel();
        ImageIcon img = new ImageIcon("node.png");// 创建图片对象
        nodeLabel.setIcon(img);
        nodeLabel.setBounds(100,100,100,img.getIconHeight());
        nodeLabel.setBackground(Color.BLUE);*/

        //layeredPane.add(nodeLabel,new Integer(Integer.MIN_VALUE));
        //panel.setOpaque(false);


        // 主窗口设置
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("GUI");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(1500,800);//整个窗体的大小
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

    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(speed);
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        notifyObservers();
    }

    /*public void GUI() {
        setTitle("图像测试");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("node.png");// 创建图片对象
        label.setIcon(img);
        panel.add(label);
        add(panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// JFrame最大化
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 让JFrame的关闭按钮起作用
        setVisible(true);// 显示JFrame
    }*/
}
