package NewGUI;

import com.jogamp.opengl.FPSCounter;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class GLRender implements GLEventListener{

    private float r;
    Bsipic m_bsipic;

    private FPSCounter fps;
    protected final float MAP = 40.0f;


    public void display(GLAutoDrawable drawable) {

    }

    public void dispose(GLAutoDrawable drawable) {

    }

    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

        GL2 gl = drawable.getGL().getGL2();

        GLU glu = new GLU();
        gl.glViewport(0, 0, 800, 600);          // 设置OpenGL视口大小。
        gl.glMatrixMode(GL2.GL_PROJECTION);         // 设置当前矩阵为投影矩阵。
        gl.glLoadIdentity();                        // 重置当前指定的矩阵为单位矩阵
        glu.gluPerspective // 设置透视图
                (54.0f, // 透视角设置为 45 度
                        (float) 800 / (float) 600, // 窗口的宽与高比
                        0.1f, // 视野透视深度:近点1.0f
                        3000.0f // 视野透视深度:始点0.1f远点1000.0f
                );
        // 这和照象机很类似，第一个参数设置镜头广角度，第二个参数是长宽比，后面是远近剪切。
        gl.glMatrixMode(GL2.GL_MODELVIEW);              // 设置当前矩阵为模型视图矩阵
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
