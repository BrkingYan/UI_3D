package NewGUI;

import com.jogamp.opengl.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class TopViewRender extends GLRender{

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.3f, 1.0f);             // 设置刷新背景色
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);// 刷新背景
        gl.glLoadIdentity();                                 // 重置当前的模型观察矩阵

        m_bsipic.DisplayTopScene();//摄像机
        m_bsipic.picterTop(1.0f, 0f, -20);         //雷达
        m_bsipic.r += 1.0f;
        if (m_bsipic.r > 360) {
            m_bsipic.r = 0;
        }

        //fps.draw();
        gl.glFlush();                                   // 更新窗口
    }
}
