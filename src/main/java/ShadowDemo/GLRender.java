package ShadowDemo;

import com.jogamp.opengl.*;

public class GLRender implements GLEventListener {

    float xRot = 0.0f;
    float yRot = 0.0f;
    private GL2 gl;

    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();
        float[] whiteLight = {0.45f, 0.45f, 0.45f, 1.0f};
        float[] sourceLight = {0.25f, 0.25f, 0.25f, 1.0f};
        float[] lightPos = {-50.f, 25.0f, 250.0f, 0.0f};

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glFrontFace(GL.GL_CCW);
        gl.glEnable(GL.GL_CULL_FACE);   //用GL_CULL_FACE符号常量调用glEnable函数表示开启多边形表面剔除功能

        // 黑色背景
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        float fZ,bZ;

        // 填充背景为背景颜色
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        fZ = 100.0f;
        bZ = -100.0f;

        // 保存矩阵状态和做旋转
        gl.glPushMatrix();
        gl.glRotatef(xRot, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);

        // 设置绘画颜色为红
        gl.glColor3f(1.0f, 0.0f, 0.0f);

        // Front Face ///////////////////////////////////
        gl.glBegin(GL2.GL_QUADS);
        //直截了当地指着Z轴,也就是指向前面的法线
        gl.glNormal3f(0.0f, 0.0f, 1.0f);

        // 正面左横
        gl.glVertex3f(-50.0f, 50.0f, fZ);
        gl.glVertex3f(-50.0f, -50.0f, fZ);
        gl.glVertex3f(-35.0f, -50.0f, fZ);
        gl.glVertex3f(-35.0f,50.0f,fZ);

        // 正面右横
        gl.glVertex3f(50.0f, 50.0f, fZ);
        gl.glVertex3f(35.0f, 50.0f, fZ);
        gl.glVertex3f(35.0f, -50.0f, fZ);
        gl.glVertex3f(50.0f,-50.0f,fZ);

        // 正面上横
        gl.glVertex3f(-35.0f, 50.0f, fZ);
        gl.glVertex3f(-35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 50.0f,fZ);

        // 正面下横
        gl.glVertex3f(-35.0f, -35.0f, fZ);
        gl.glVertex3f(-35.0f, -50.0f, fZ);
        gl.glVertex3f(35.0f, -50.0f, fZ);
        gl.glVertex3f(35.0f, -35.0f,fZ);



        //顶外 ////////////////////////////
        // Normal points up Y axis
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-50.0f, 50.0f, fZ);
        gl.glVertex3f(50.0f, 50.0f, fZ);
        gl.glVertex3f(50.0f, 50.0f, bZ);
        gl.glVertex3f(-50.0f,50.0f,bZ);

        //底外
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, fZ);
        gl.glVertex3f(-50.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f, -50.0f, fZ);

        //左外
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(50.0f, 50.0f, fZ);
        gl.glVertex3f(50.0f, -50.0f, fZ);
        gl.glVertex3f(50.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f, 50.0f, bZ);

        //右外
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(-50.0f, 50.0f, fZ);
        gl.glVertex3f(-50.0f, 50.0f, bZ);
        gl.glVertex3f(-50.0f, -50.0f, bZ);
        gl.glVertex3f(-50.0f, -50.0f, fZ);
        gl.glEnd();

        gl.glFrontFace(GL.GL_CW);		// 设置CW方向为“正面”，CW即ClockWise，顺时针

        gl.glBegin(GL2.GL_QUADS);
        // Back section
        // Pointing straight out Z
        gl.glNormal3f(0.0f, 0.0f, -1.0f);

        //后面左横
        gl.glVertex3f(-50.0f, 50.0f, bZ);
        gl.glVertex3f(-50.0f, -50.0f, bZ);
        gl.glVertex3f(-35.0f, -50.0f, bZ);
        gl.glVertex3f(-35.0f,50.0f,bZ);

        //后面右横
        gl.glVertex3f(50.0f, 50.0f, bZ);
        gl.glVertex3f(35.0f, 50.0f, bZ);
        gl.glVertex3f(35.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f,-50.0f,bZ);

        //后面上横
        gl.glVertex3f(-35.0f, 50.0f, bZ);
        gl.glVertex3f(-35.0f, 35.0f, bZ);
        gl.glVertex3f(35.0f, 35.0f, bZ);
        gl.glVertex3f(35.0f, 50.0f,bZ);

        // 后面下横
        gl.glVertex3f(-35.0f, -35.0f, bZ);
        gl.glVertex3f(-35.0f, -50.0f, bZ);
        gl.glVertex3f(35.0f, -50.0f, bZ);
        gl.glVertex3f(35.0f, -35.0f,bZ);

        // Insides /////////////////////////////
        gl.glColor3f(0.75f, 0.75f, 0.75f);

        // Normal points up Y axis
        //内上
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl. glVertex3f(-35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 35.0f, bZ);
        gl.glVertex3f(-35.0f,35.0f,bZ);

        //内下
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-35.0f, -35.0f, fZ);
        gl.glVertex3f(-35.0f, -35.0f, bZ);
        gl.glVertex3f(35.0f, -35.0f, bZ);
        gl.glVertex3f(35.0f, -35.0f, fZ);

        //内左
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(-35.0f, 35.0f, fZ);
        gl.glVertex3f(-35.0f, 35.0f, bZ);
        gl.glVertex3f(-35.0f, -35.0f, bZ);
        gl.glVertex3f(-35.0f, -35.0f, fZ);

        //内右
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, -35.0f, fZ);
        gl.glVertex3f(35.0f, -35.0f, bZ);
        gl.glVertex3f(35.0f, 35.0f, bZ);
        gl.glEnd();

        gl.glFrontFace(GL.GL_CCW);		//设置CCW方向为“正面”，CCW即CounterClockWise，逆时针

        // Restore the matrix state   还原状态矩阵
        gl.glPopMatrix();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        float nRange = 100.0f;

        float w=(float)width;
        float h=(float)height;

        // 防止为零
        if (height == 0) {
            height = 1;
        }

        //视口设置为窗口尺寸
        gl.glViewport(0, 0, width, height);

        // Reset projection matrix stack
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        //建立剪辑视图,正投影（左，右，底部，顶部，近，远）
        if (w <= h) {
            gl.glOrtho(-nRange, nRange, -nRange * h / w, nRange * h / w, -nRange-50, nRange);
        } else {
            gl.glOrtho(-nRange * w / h, nRange * w / h, -nRange, nRange, -nRange-50, nRange);
        }

        // 重置模型观察矩阵堆栈
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
