package D3_Scene;

import com.jogamp.opengl.FPSCounter;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Render2 implements GLEventListener{

    private float r;
    Bsipic m_bsipic;

    private FPSCounter fps;
    private final float MAP = 40.0f;
    boolean isspacepress;


    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.3f, 1.0f);             // ����ˢ�±���ɫ
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);// ˢ�±���
        gl.glLoadIdentity();                                 // ���õ�ǰ��ģ�͹۲����

        m_bsipic.DisplayScene();
        m_bsipic.DrawGround();                     //��ɫ���������

        m_bsipic.airplane(MAP+6, 10.0f, -50.0f);                   //��Ϸɻ�

        m_bsipic.picter2(MAP + 6, 0f, -MAP);         //�״�
        m_bsipic.r += 1.0f;
        if (m_bsipic.r > 360) {
            m_bsipic.r = 0;
        }



        if(isspacepress)
        {
            m_bsipic.drawMeinv();
        }

        //fps.draw();
        gl.glFlush();                                   // ���´���

    }

    public void dispose(GLAutoDrawable drawable) {

    }

    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

        GL2 gl = drawable.getGL().getGL2();

        GLU glu = new GLU();
        gl.glViewport(0, 0, 800, 600);          // ����OpenGL�ӿڴ�С��
        gl.glMatrixMode(GL2.GL_PROJECTION);         // ���õ�ǰ����ΪͶӰ����
        gl.glLoadIdentity();                        // ���õ�ǰָ���ľ���Ϊ��λ����
        glu.gluPerspective // ����͸��ͼ
                (54.0f, // ͸�ӽ�����Ϊ 45 ��
                        (float) 800 / (float) 600, // ���ڵĿ���߱�
                        0.1f, // ��Ұ͸�����:����1.0f
                        3000.0f // ��Ұ͸�����:ʼ��0.1fԶ��1000.0f
                );
        // �������������ƣ���һ���������þ�ͷ��Ƕȣ��ڶ��������ǳ���ȣ�������Զ�����С�
        gl.glMatrixMode(GL2.GL_MODELVIEW);              // ���õ�ǰ����Ϊģ����ͼ����
        //gl.glLoadIdentity();




        m_bsipic = new Bsipic(gl);
        m_bsipic.light(0, 10, -20, 128);
        //fps = new FPSCounter(drawable, 36);





    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w,
                        int h) {
        // TODO Auto-generated method stub
    }




}
