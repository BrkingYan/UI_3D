package Display;

import Display.Model.PaintUtil;

import javax.swing.*;
import java.awt.*;

//��νṹ  Frame > JPanel > JLabel

/*
* ���ڷ��þ�̬���
* */
public class MainFrame extends JFrame {


    private float speed = 0;
    private Painter painter;

    public MainFrame(){


        initMainFrame();
    }


    private void initMainFrame(){
        //��ʾ��ǰ��Ļ�ֱ���
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        System.out.println(screenHeight + "," + screenWidth);


        //��Ӿ�̬�����ͼƬ
        /*JLabel nodeLabel = new JLabel();
        ImageIcon img = new ImageIcon("node.png");// ����ͼƬ����
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


        // ����������
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("GUI");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(1500,800);//��������Ĵ�С
        this.setBounds(0, 0, 1500 * PaintUtil.xTrans, 800 * PaintUtil.yTrans);

    }


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
