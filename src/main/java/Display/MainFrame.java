package Display;

import Display.Model.PaintUtil;

import javax.swing.*;
import java.awt.*;

//层次结构  Frame > JPanel > JLabel

/*
* 用于放置静态组件
* */
public class MainFrame extends JFrame {


    private float speed = 0;
    private Painter painter;

    public MainFrame(){


        initMainFrame();
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

        painter = new Painter();
        this.add(painter);

        //this.getLayeredPane().add(painter);
        //painter.add(button);
        //button.setBounds(0,0,500,500);
        //painter.setOpaque(false);


        // 主窗口设置
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("GUI");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(1500,800);//整个窗体的大小
        this.setBounds(0, 0, 1366 * PaintUtil.xTrans, 730 * PaintUtil.yTrans);

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
