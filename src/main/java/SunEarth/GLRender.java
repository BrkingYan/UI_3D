package SunEarth;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class GLRender implements GLEventListener {

    GL2 gl;
    GLU glu;
    GLUT glut;
    float whiteLight[] = {0.2f, 0.2f, 0.2f, 1.0f};
    float sourceLight[] = {0.8f, 0.8f, 0.8f, 1.0f};
    float lightPos[] = {0.0f, 0.0f, 0.0f, 1.0f};


    float fMoonRot = 0.0f;
    float fEarthRot = 0.0f;

    public void init(GLAutoDrawable drawable) {
        gl =  drawable.getGL().getGL2();    //主要就是这里发生变化
        glu = new GLU();
        glut=new GLUT();


        // Light values and coordinates
        gl.glEnable(GL2.GL_DEPTH_TEST);	// 启用深度测试
        gl.glFrontFace(GL2.GL_CCW);		//设置CCW方向为“正面”，CCW即CounterClockWise，逆时针
        gl.glEnable(GL2.GL_CULL_FACE);		//用GL_CULL_FACE符号常量调用glEnable函数表示开启多边形表面剔除功能

        // 启用灯光
        gl.glEnable(GL2.GL_LIGHTING);

        // 设置灯光
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, whiteLight, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, sourceLight, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
        gl.glEnable(GL2.GL_LIGHT0);

        // 启用材质
        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        // 决定对物体的正面还是反面，对环境光、镜面光还是漫射光进行颜色跟踪。
        //第一个参数可以取GL_FRONT、GL_BACK、GL_FRONT_AND_BACK中的任意一种，
        //第二个参数可以取GL_AMBIENT、GL_DIFFUSE、GL_AMBIENT_AND_DIFFUSE、GL_SPECULAR中的任意一种。
        gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE);

        // 黑色背景
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);



    }

    public void display(GLAutoDrawable drawable) {

        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性

        gl.glPushMatrix();
        glut.glutWireCylinder(0.5,0.6,20,20);
        gl.glRotatef( 90.0f, 0.0f, 1.0f, 0.0f);  //雷达旋转2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glPopMatrix();


        /*// 填充背景为背景颜色
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        // 保存矩阵状态和做旋转
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();

        // 画图点移动到Z里300的位置,负为向里,正为向程序员.
        gl.glTranslatef(0.0f, 0.0f, -300.0f);

        // Set material color, Red
        // 太阳
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glColor3ub((byte)255, (byte)255, (byte)0);
        glut.glutSolidSphere(15.0f, 30, 17);
        gl.glEnable(GL2.GL_LIGHTING);

        // 重新设置灯光到太阳的位置
        //gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_POSITION,lightPos,0);

        glut.glutSolidCylinder(1.5,0.6,20,20);

        // 围绕Y轴旋转矩阵
        gl.glRotatef(fEarthRot, 0.0f, 1.0f, 0.0f);

        // 画地球
        gl.glColor3ub((byte)0,(byte)200,(byte)255);
        gl.glTranslatef(105.0f,0.0f,0.0f);
        glut.glutSolidSphere(15.0f, 30, 17);


        // 还原状态矩阵
        gl.glPopMatrix();	// Modelview matrix


        //地球轨道每步5度,旋转度数
        fEarthRot += 2.0f;
        if(fEarthRot > 360.0f)
            fEarthRot = 0.0f;*/
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        float fAspect;

        // 防止为零
        if (height == 0) {
            height = 1;
        }

        //视口设置为窗口尺寸
        gl.glViewport(0, 0, width, height);

        fAspect = (float) width / height;

        // Reset projection matrix stack
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();



        //透视投影 眼角度,比例,近可视,远可视
        glu.gluPerspective(53.0f, fAspect, 1.0, 400.0);

        // 重置模型观察矩阵堆栈
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

    }



    public void dispose(GLAutoDrawable arg0) {

    }
}
