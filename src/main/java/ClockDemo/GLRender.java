package ClockDemo;


import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;


/*
 *
 * @author Administrator
 */
public class GLRender implements GLEventListener {

    private float theta = 0.0f;
    private float minute = 0;
    private float hour = 0;
    private float second = 0;
    private GLU glu = new GLU();
    private GL2 gl;

    Timestamp ts;
    String dateStr1;


    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable drawable) {
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);


        System.out.println(hour + " " + minute + " " + second);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        //------------»­µ×ÅÌ------------//
        gl.glPushMatrix();
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        for (int i = 0; i <= 390; i += 30) {
            float p = (float) (i * 3.14 / 180);
            gl.glVertex3f((float) Math.sin(p) / 1.5f, (float) Math.cos(p) / 1.5f, 0.0f);
        }
        gl.glEnd();


        //------------»­ÕëÅÌ------------//
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(0.4f, 0.4f, 0.0f);
        for (int i = 0; i <= 365; i += 5) {
            float p = (float) (i * 3.14 / 180);
            gl.glVertex3f((float) Math.sin(p) / 1.6f, (float) Math.cos(p) / 1.6f, 0.0f);
        }
        gl.glEnd();

        //------------»­¿Ìµã------------//
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        for (int i = 0; i <= 365; i += 30) {
            float p = (float) (i * 3.14 / 180);
            /*glBegin(GL_POLYGON);
            glVertex3f((float)sin(p)/2.2,(float)cos(p)/2.2,0.0f);
            glVertex3f((float)sin(p)/2.4,(float)cos(p)/2.4,0.0f);
            glVertex3f((float)sin(p+0.02)/2.2,(float)cos(p+0.02)/2.2,0.0f);
            glVertex3f((float)sin(p+0.02)/2.4,(float)cos(p+0.02)/2.4,0.0f);
            glEnd();*/
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f((float) Math.sin(p) / 2.0f, (float) Math.cos(p) / 2.0f, 0.0f);
            gl.glVertex3f((float) Math.sin(p) / 1.8f, (float) Math.cos(p) / 1.8f, 0.0f);
            gl.glEnd();
        }
        gl.glEnd();
        gl.glPopMatrix();


        //------------»­ÃëÕë------------//
        gl.glPushMatrix();//¾ØÕóÈëÕ»£¬·ÀÖ¹µ÷ÓÃº¯Êý±ä»»ºó¸Ä±äÏÖÓÐ¾ØÕó
        gl.glRotatef(second / 60 * -360.0f, 0.0f, 0.0f, 1.0f);//ÊµÏÖÈÆ×ø±êÖáÐý×ª
        /*glBegin (GL_TRIANGLES);
        glColor3f (1.0f, 0.0f, 0.0f);   glVertex2f (0.0f, 1.0f);
        glColor3f (0.0f, 1.0f, 0.0f);   glVertex2f (0.87f, -0.5f);
        glColor3f (0.0f, 0.0f, 1.0f);   glVertex2f (-0.87f, -0.5f);
        glEnd ();*/
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex2f(0.0f, -0.1f);
        gl.glVertex2f(0.0f, 0.5f);
        gl.glEnd();
        gl.glPopMatrix();

        //------------»­·ÖÕë------------//
        gl.glPushMatrix();
        gl.glRotatef(minute / 60.0f * -360.0f, 0.0f, 0.0f, 1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex2f(0.0f, -0.07f);
        gl.glVertex2f(0.0f, 0.4f);
        gl.glEnd();
        gl.glPopMatrix();

        //------------»­Ê±Õë------------//
        gl.glPushMatrix();
        gl.glRotatef((hour / 12.0f * -360.0f) - minute / 60.0f * 30.0f, 0.0f, 0.0f, 1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(0.0f, -0.05f);
        gl.glVertex2f(0.0f, 0.3f);
        gl.glEnd();
        gl.glPopMatrix();

        //------------Ô²ÐÄ------------//
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_POLYGON_OFFSET_FILL);
        gl.glVertex2f(0.01f, 0.0f);
        gl.glVertex2f(0.0f, 0.01f);
        gl.glVertex2f(-0.01f, 0.0f);
        gl.glVertex2f(0.0f, -0.01f);
        gl.glEnd();

        gl.glFlush();

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }


    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();
        /*gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);*/

    }
}
