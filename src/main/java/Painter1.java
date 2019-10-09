import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

/*
*  3Dͼ
* */
public class Painter1 extends Painter implements GLEventListener {

    private GLU glu = new GLU();
    private float shift;
    private float speed;

    //��ʼ��OpenGL
    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(255f, 255f, 255f, 255f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    //��ͼ
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();                       // Reset The View
        gl.glTranslatef(0f, 0f, -6.0f);
        gl.glRotatef(speed, 0.0f, 1.0f, 0.0f);
        gl.glBegin(GL2.GL_QUADS);

        //3D
        gl.glVertex3f(0f, 1f, 0f);
        gl.glVertex3f(0f, -1f, 0f);
        gl.glVertex3f(0f, 0f,  1f);
        gl.glVertex3f(0f, 0f,  -1f);


        gl.glEnd();
        gl.glFlush();
        //change the speeds here
        //shift = speed;
        //System.out.println(123);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int width, int height) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        if(height <=0)
            height =1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void update(float speed) {
        this.speed = speed;
    }

    public void update(float roll_degree, float pit_degree) {

    }

    public void update1(String str) {

    }

    public void update2(float roll_degree, float pit_degree) {

    }
}
