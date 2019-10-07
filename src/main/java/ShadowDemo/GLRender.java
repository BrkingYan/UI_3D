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
        gl.glEnable(GL.GL_CULL_FACE);   //��GL_CULL_FACE���ų�������glEnable������ʾ��������α����޳�����

        // ��ɫ����
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        float fZ,bZ;

        // ��䱳��Ϊ������ɫ
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        fZ = 100.0f;
        bZ = -100.0f;

        // �������״̬������ת
        gl.glPushMatrix();
        gl.glRotatef(xRot, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(yRot, 0.0f, 1.0f, 0.0f);

        // ���û滭��ɫΪ��
        gl.glColor3f(1.0f, 0.0f, 0.0f);

        // Front Face ///////////////////////////////////
        gl.glBegin(GL2.GL_QUADS);
        //ֱ���˵���ָ��Z��,Ҳ����ָ��ǰ��ķ���
        gl.glNormal3f(0.0f, 0.0f, 1.0f);

        // �������
        gl.glVertex3f(-50.0f, 50.0f, fZ);
        gl.glVertex3f(-50.0f, -50.0f, fZ);
        gl.glVertex3f(-35.0f, -50.0f, fZ);
        gl.glVertex3f(-35.0f,50.0f,fZ);

        // �����Һ�
        gl.glVertex3f(50.0f, 50.0f, fZ);
        gl.glVertex3f(35.0f, 50.0f, fZ);
        gl.glVertex3f(35.0f, -50.0f, fZ);
        gl.glVertex3f(50.0f,-50.0f,fZ);

        // �����Ϻ�
        gl.glVertex3f(-35.0f, 50.0f, fZ);
        gl.glVertex3f(-35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 50.0f,fZ);

        // �����º�
        gl.glVertex3f(-35.0f, -35.0f, fZ);
        gl.glVertex3f(-35.0f, -50.0f, fZ);
        gl.glVertex3f(35.0f, -50.0f, fZ);
        gl.glVertex3f(35.0f, -35.0f,fZ);



        //���� ////////////////////////////
        // Normal points up Y axis
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-50.0f, 50.0f, fZ);
        gl.glVertex3f(50.0f, 50.0f, fZ);
        gl.glVertex3f(50.0f, 50.0f, bZ);
        gl.glVertex3f(-50.0f,50.0f,bZ);

        //����
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(-50.0f, -50.0f, fZ);
        gl.glVertex3f(-50.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f, -50.0f, fZ);

        //����
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(50.0f, 50.0f, fZ);
        gl.glVertex3f(50.0f, -50.0f, fZ);
        gl.glVertex3f(50.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f, 50.0f, bZ);

        //����
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(-50.0f, 50.0f, fZ);
        gl.glVertex3f(-50.0f, 50.0f, bZ);
        gl.glVertex3f(-50.0f, -50.0f, bZ);
        gl.glVertex3f(-50.0f, -50.0f, fZ);
        gl.glEnd();

        gl.glFrontFace(GL.GL_CW);		// ����CW����Ϊ�����桱��CW��ClockWise��˳ʱ��

        gl.glBegin(GL2.GL_QUADS);
        // Back section
        // Pointing straight out Z
        gl.glNormal3f(0.0f, 0.0f, -1.0f);

        //�������
        gl.glVertex3f(-50.0f, 50.0f, bZ);
        gl.glVertex3f(-50.0f, -50.0f, bZ);
        gl.glVertex3f(-35.0f, -50.0f, bZ);
        gl.glVertex3f(-35.0f,50.0f,bZ);

        //�����Һ�
        gl.glVertex3f(50.0f, 50.0f, bZ);
        gl.glVertex3f(35.0f, 50.0f, bZ);
        gl.glVertex3f(35.0f, -50.0f, bZ);
        gl.glVertex3f(50.0f,-50.0f,bZ);

        //�����Ϻ�
        gl.glVertex3f(-35.0f, 50.0f, bZ);
        gl.glVertex3f(-35.0f, 35.0f, bZ);
        gl.glVertex3f(35.0f, 35.0f, bZ);
        gl.glVertex3f(35.0f, 50.0f,bZ);

        // �����º�
        gl.glVertex3f(-35.0f, -35.0f, bZ);
        gl.glVertex3f(-35.0f, -50.0f, bZ);
        gl.glVertex3f(35.0f, -50.0f, bZ);
        gl.glVertex3f(35.0f, -35.0f,bZ);

        // Insides /////////////////////////////
        gl.glColor3f(0.75f, 0.75f, 0.75f);

        // Normal points up Y axis
        //����
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl. glVertex3f(-35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, 35.0f, bZ);
        gl.glVertex3f(-35.0f,35.0f,bZ);

        //����
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-35.0f, -35.0f, fZ);
        gl.glVertex3f(-35.0f, -35.0f, bZ);
        gl.glVertex3f(35.0f, -35.0f, bZ);
        gl.glVertex3f(35.0f, -35.0f, fZ);

        //����
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(-35.0f, 35.0f, fZ);
        gl.glVertex3f(-35.0f, 35.0f, bZ);
        gl.glVertex3f(-35.0f, -35.0f, bZ);
        gl.glVertex3f(-35.0f, -35.0f, fZ);

        //����
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(35.0f, 35.0f, fZ);
        gl.glVertex3f(35.0f, -35.0f, fZ);
        gl.glVertex3f(35.0f, -35.0f, bZ);
        gl.glVertex3f(35.0f, 35.0f, bZ);
        gl.glEnd();

        gl.glFrontFace(GL.GL_CCW);		//����CCW����Ϊ�����桱��CCW��CounterClockWise����ʱ��

        // Restore the matrix state   ��ԭ״̬����
        gl.glPopMatrix();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        float nRange = 100.0f;

        float w=(float)width;
        float h=(float)height;

        // ��ֹΪ��
        if (height == 0) {
            height = 1;
        }

        //�ӿ�����Ϊ���ڳߴ�
        gl.glViewport(0, 0, width, height);

        // Reset projection matrix stack
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        //����������ͼ,��ͶӰ�����ң��ײ�������������Զ��
        if (w <= h) {
            gl.glOrtho(-nRange, nRange, -nRange * h / w, nRange * h / w, -nRange-50, nRange);
        } else {
            gl.glOrtho(-nRange * w / h, nRange * w / h, -nRange, nRange, -nRange-50, nRange);
        }

        // ����ģ�͹۲�����ջ
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
