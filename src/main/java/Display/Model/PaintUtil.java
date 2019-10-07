package Display.Model;

import java.awt.*;

public class PaintUtil {

    /*
    * ��Ļ��Ϣ
    * */
    public static int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int originWidth = 1366;
    private static final int originHeight = 768;
    public static int xTrans = screenWidth / originWidth;
    public static int yTrans = screenHeight / originHeight;

    /*
    * �������������
    * */
    //Բ������
    public static final int originX = 340 * xTrans;
    public static final int originY = 350 * yTrans;
    //�᳤��
    public static final int axisWidth = 330 * xTrans;
    public static final int axisHeight = 330 * yTrans;
    //����(һ��̶ȱ�ʾ�Ļ�ͼ����)
    private static int stepX;
    private static int stepY;

    public static void drawAxis(Graphics2D g){

        Axis axis = new Axis(originX,originY);
        drawAxis(g,15,axis);
        //System.out.println(screenHeight);
        //System.out.println(screenWidth);
    }

    /*
    * ����ͼ������
    * */
    private static void drawAxis(Graphics2D g,int N,Axis axis){
        int oX = axis.getX();
        int oY = axis.getY();
        //��������
        g.drawLine(oX-axisWidth,oY,oX+axisWidth,oY);
        g.drawLine(oX,oY-axisHeight,oX,oY+axisHeight);

        //���̶ȵ�
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

    //���ߺ�����
    public static void drawDynamic(Graphics2D g,float x,float y){
        int xx = (int)(x * 10 * stepX + originX);
        int yy = (int)(-y * 10 * stepY + originY);
        g.drawLine(originX,originY,xx,yy);
        g.drawOval(xx - 5*xTrans,yy - 5*yTrans,9,9);
        g.drawString("("+x*10 + "," + y*10 + ")",xx,yy);
    }

    public static void drawInterLine(Graphics2D g){
        g.drawLine(originX + axisWidth + 13*xTrans,0,originX + axisWidth + 13*xTrans,screenHeight);

    }

    /**************************************************************************************/

    /*
    * ����ͼ������
    * */
    //Բ������
    public static final int originX_side = 1020 * xTrans;
    public static final int originY_side = 350 * yTrans;

    public static void drawAxisSideView(Graphics2D g){
        Axis axis = new Axis(originX_side,originY_side);
        drawAxisSide(g,15,axis);

    }

    //����ͼ������
    private static void drawAxisSide(Graphics2D g,int N,Axis axis){
        int oX = axis.getX();
        int oY = axis.getY();

        //���ӽڵ�
        g.drawOval(originX_side,originY_side - axisHeight,5,5);

        //������������
        g.drawLine(oX-axisWidth,oY,oX+axisWidth,oY);

        //���̶ȵ�
        stepX = axisWidth / N;
        //X
        drawPointX(g,N,stepX,originX_side,originY_side);
        g.drawLine(originX_side,originY_side,originX_side,originY_side - 5*yTrans);
        g.drawString("0",originX_side,originY_side + 15 * yTrans);
    }

    public static void drawDynamicSide(Graphics2D g,float x){
        int nodeX = originX_side;
        int nodeY = originY_side - axisHeight;

        int xx = nodeX + (int) (x * 10) * stepX;
        g.drawLine(nodeX,nodeY,xx,nodeY + axisHeight);
        g.drawOval(xx - 5*xTrans,nodeY + axisHeight - 5*yTrans,10,10);
    }

}
