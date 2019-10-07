package NewGUI;

import com.jogamp.opengl.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class TopViewRender extends GLRender{

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.3f, 1.0f);             // ����ˢ�±���ɫ
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);// ˢ�±���
        gl.glLoadIdentity();                                 // ���õ�ǰ��ģ�͹۲����

        m_bsipic.DisplayTopScene();//�����
        m_bsipic.picterTop(1.0f, 0f, -20);         //�״�
        m_bsipic.r += 1.0f;
        if (m_bsipic.r > 360) {
            m_bsipic.r = 0;
        }

        //fps.draw();
        gl.glFlush();                                   // ���´���
    }
}
