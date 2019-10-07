import javax.swing.*;
import java.awt.*;

/*
 * 用于绘制动态点线
 * */
public class PaintBoard extends Painter {

    private int count = 0;
    private Node node_side_view;
    private Node node_top_view;

    private boolean change = true;

    private double angle_side = 31;//侧视图偏转角
    private double angle_top = 0;//俯视图偏转角
    private float speed = 0;
    private static final int length = 100;//绳长

    public PaintBoard(){
        //this.setSize(500,500);
        this.setBackground(Color.WHITE);
        initComponent();
    }

    private void initComponent(){
        node_side_view = new Node(200,100);//测试图节点位置
        node_top_view = new Node(200,400);//俯视图节点位置
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(6));
        g2d.setColor(Color.BLACK);

        paintSideView(g2d,angle_side,length);

        paintTopView(g2d,angle_top,length);

        /****** test ******/
        if (change){
            angle_side += 3 * speed;
        }else {
            angle_side -= 3 * speed;
        }

        if (angle_side >= 150){
            change = !change;
        }
        if (angle_side <= 30){
            change = !change;
        }

        angle_top = (angle_top + 5 * speed) % 360;
        /****** test ******/

        repaint();
    }

    /*
     * 侧视图
     * */
    private void paintSideView(Graphics g,double angle,int len){
        double radian = (angle / 180) * Math.PI;
        g.drawLine(node_side_view.x,node_side_view.y,
                node_side_view.x - (int) (len * Math.cos(radian)),
                node_side_view.y + (int) (len * Math.sin(radian)));

    }

    /*
     * 俯视图
     * */
    private void paintTopView(Graphics g,double angle,int len){
        double radian = (angle / 180) * Math.PI;
        g.drawLine(node_top_view.x,node_top_view.y,
                node_top_view.x - (int) (len * Math.cos(radian)),
                node_top_view.y + (int) (len * Math.sin(radian)));
    }

    public void update(float speed) {
        this.speed = speed;
    }


    private class Node{
        int x;
        int y;
        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
