package Display.Model;

import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PaintUtil {

    /*
    * 屏幕信息
    * */
    public static int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int originWidth = 1366;
    private static final int originHeight = 768;
    public static int xTrans = screenWidth / originWidth;
    public static int yTrans = screenHeight / originHeight;

    //字体
    public static final Font basicFont = new   Font("basic",   Font.PLAIN,   14);
    public static final Font boldFont = new   Font("bold",   Font.BOLD,   14);
    public static final Font titleFont = new   Font("title",   Font.ITALIC,   17);
    public static final Font subTitleFont = new   Font("subTitle",   Font.ITALIC,   14);

    /*
    * 坐标轴参数配置
    * */
    //圆心坐标
    public static final int originX = 340 * xTrans;
    public static final int originY = 350 * yTrans;
    //轴长短
    public static final int axisWidth = 330 * xTrans;
    public static final int axisHeight = 330 * yTrans;
    //步长(一格刻度表示的绘图长度)
    private static int stepX;
    private static int stepY;
    //绳长
    private static final int lineLength = 200;

    public static void drawAxis(Graphics2D g,int scaleX,int scaleY){

        Axis axis = new Axis(originX,originY);
        drawAxis(g,15,axis,scaleX,scaleY);
        //System.out.println(screenHeight);
        //System.out.println(screenWidth);
    }

    /*
    * 俯视图坐标轴
    * */
    private static void drawAxis(Graphics2D g,int N,Axis axis,int scaleX,int scaleY){
        int oX = axis.getX();
        int oY = axis.getY();
        //画出两轴
        g.drawLine(oX-axisWidth,oY,oX+axisWidth,oY);
        g.drawLine(oX,oY-axisHeight,oX,oY+axisHeight);

        //画刻度点
        stepX = axisWidth / N;
        stepY = axisHeight / N;
        //X
        drawPointX(g,N,stepX,originX,originY);
        //Y
        drawPointY(g,N,stepY,originX,originY);
    }

    //X
    private static void drawPointX(Graphics2D g,int N,int step,int originX,int originY){
        //X+
        for (int i = 1;i<=N;i++){
            g.drawLine(originX + i*step,originY,originX + i*step,originY - 5);
            g.drawString("" + i,originX + i*step - 5*xTrans,originY+15*yTrans);
        }

        //X-
        for (int i = 1;i<=N;i++){
            g.drawLine(originX - i*step,originY,originX - i*step,originY - 5);
            g.drawString("-" + i,originX - i*step - 5*xTrans,originY+15*yTrans);
        }
    }

    //Y
    private static void drawPointY(Graphics2D g,int N,int step,int originX,int originY){
        //Y+
        for (int i = 1;i<=N;i++){
            g.drawLine(originX,originY - i * step,originX + 5,originY - i * step);
            g.drawString("" + i,originX - 15 * xTrans,originY - i * step + 3 * yTrans);
        }

        //Y-
        for (int i = 1;i<=N;i++){
            g.drawLine(originX,originY + i * step,originX + 5,originY + i * step);
            g.drawString("-" + i,originX -15 * xTrans - 3 * xTrans,originY + i * step);
        }
    }

    //画线和物体
    public static void drawDynamic(Graphics2D g, float x, float y){

        int xx = (int)(x * 10 * stepX + originX);
        int yy = (int)(-y * 10 * stepY + originY);
            //g.drawLine(originX,originY,xx,yy);
            g.drawOval(xx - 5*xTrans,yy - 5*yTrans,5,5);
            //g.drawString("("+x*10 + "," + y*10 + ")",xx,yy);
    }

    //画轨迹
    public static void drawDynamicAll(Graphics2D g,  ArrayList<Point> queue){
        for (int i = 0;i<queue.size();i++){
            Point p = queue.get(i);
            float x = p.getX();
            float y = p.getY();
            drawDynamic(g,x,y);

            /*if (i > 0){
                Point pre = queue.get(i-1);
                int x1 = (int)(x * 10 * stepY + originY);
                int y1 = (int)(-y * 10 * stepX + originX);
                int xx = (int)(pre.getX() * 10 * stepY + originY);
                int yy = (int)(-pre.getY() * 10 * stepX + originX);
                g.drawLine(x1,y1,xx,yy);
            }*/
        }
    }

    //画分隔线
    public static void drawInterLine(Graphics2D g){
        g.drawLine(originX + axisWidth + 13*xTrans,0,originX + axisWidth + 13*xTrans,screenHeight);

    }

    /**************************************************************************************/

    /*
    * 侧视图坐标轴
    * */
    //圆心坐标
    public static final int originX_side = 1020 * xTrans;
    public static final int originY_side = 50 * yTrans;
    //绳子节点坐标
    public static final int nodeX = 1020 * xTrans;
    public static final int nodeY = 50 * yTrans;


    public static void drawAxisSideView(Graphics2D g){
        Axis axis = new Axis(originX_side,originY_side);
        drawAxisSide(g,15,axis);
    }

    //侧视图坐标轴
    private static void drawAxisSide(Graphics2D g,int N,Axis axis){
        int oX = axis.getX();
        int oY = axis.getY();

        //虚线
        g.setColor(Color.gray);
        g.drawLine(nodeX,nodeY - 30,nodeX,nodeY + lineLength + 50);
        g.setColor(Color.black);

        //绳子节点
        //g.drawOval(originX_side,originY_side - axisHeight,5,5);
        //g.drawOval(originX_side,originY_side - axisHeight - 10*yTrans,5,5);

        //画出坐标轴轴
        //g.drawLine(oX-axisWidth,oY,oX+axisWidth,oY);

        //画刻度点
        /*stepX = axisWidth / N;
        //X
        drawPointX(g,N,stepX,originX_side,originY_side);
        g.drawLine(originX_side,originY_side,originX_side,originY_side - 5*yTrans);
        g.drawString("0",originX_side,originY_side + 15 * yTrans);*/
    }

    //画摆动图动态
    public static void drawDynamicSide(Graphics2D g,float degree){
        /*int nodeX = originX_side;
        int nodeY = originY_side - 300;*/


        //int xx = nodeX + (int) (x * 10) * stepX;
        int xx;
        int yy;
        //System.out.println(axisHeight + "height");
        xx = nodeX + (int)(lineLength *  Math.sin((double)degree * Math.PI / 180.0));
        yy = nodeY + (int)(lineLength * Math.cos((double)degree * Math.PI / 180.0));

        g.setStroke(new BasicStroke(3.0f));
        //绳子
        g.drawLine(nodeX,nodeY,xx,yy);
        //物体
        g.drawOval(xx - 5*xTrans,yy - 5*yTrans,10,10);
        g.setStroke(new BasicStroke(1.0f));
    }

    //画出位移数据显示
    public static void drawMoveData(Graphics2D g,int xPlot_x,int xPlot_y,int yPlot_x,int yPlot_y,float x,float y){
        g.setStroke(new BasicStroke(3.0f));
//        g.drawString(String.format("%.2f m/s", speed),x,y);
        g.drawString(String.format("%.2f m", y),xPlot_x,xPlot_y);
        g.drawString(String.format("%.2f m", x),yPlot_x,yPlot_y);
        //g.drawString(y+"",yPlot_x,yPlot_y);
        g.setStroke(new BasicStroke(1.0f));
    }

    //画速度数据
    public static void drawSpeedData(Graphics2D g,float speed,int x,int y){
        g.setStroke(new BasicStroke(3.0f));
        g.drawString(String.format("%.2f m/s", speed),x,y);
        g.setStroke(new BasicStroke(1.0f));
    }

    //画二维摆动图的偏转角
    public static void drawRollDegree(Graphics2D g,float degree,int x,int y){
        g.setStroke(new BasicStroke(3.0f));
        g.setFont(basicFont);
        g.drawString(String.format("%.2f °", degree),x,y);
        g.setStroke(new BasicStroke(1.0f));
    }

}
