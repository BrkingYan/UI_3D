package GUI;

import RefDemos.CubeTexture1;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class Main {

    private static Callback callback;
    private static float turn;

    public interface Callback{
        void update(float turn);
    }

    public static void main(String[] args) throws InterruptedException {
        //3D
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        View3D r = new View3D();
        glcanvas.addGLEventListener(r);
        glcanvas.setSize(400, 400);
        final JFrame frame = new JFrame ("3D");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        frame.setBounds(10,10,400,400);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true );
        animator.start();

        //top view
        final GLProfile profile1 = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities1 = new GLCapabilities(profile1);
        // The canvas
        final GLCanvas glcanvas1 = new GLCanvas(capabilities1);
        ViewTop r1 = new ViewTop();
        glcanvas1.addGLEventListener(r1);
        glcanvas1.setSize(400, 400);
        final JFrame frame1 = new JFrame (" Top View");
        frame1.getContentPane().add(glcanvas1);
        frame1.setSize(frame1.getContentPane().getPreferredSize());
        frame1.setVisible(true);
        frame.setBounds(110,10,400,400);
        final FPSAnimator animator1 = new FPSAnimator(glcanvas1, 300,true );
        animator1.start();

        //side view
        final GLProfile profile2 = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities2 = new GLCapabilities(profile2);
        // The canvas
        final GLCanvas glcanvas2 = new GLCanvas(capabilities2);
        ViewSide r2 = new ViewSide();
        callback = r2;
        glcanvas2.addGLEventListener(r2);
        glcanvas2.setSize(400, 400);
        final JFrame frame2 = new JFrame (" Side View");
        frame2.getContentPane().add(glcanvas2);
        frame2.setSize(frame2.getContentPane().getPreferredSize());
        frame2.setVisible(true);
        frame.setBounds(210,10,400,400);
        final FPSAnimator animator2 = new FPSAnimator(glcanvas2, 300,true );
        animator2.start();

        while (true){
            turn = - turn;
            callback.update(turn);
            Thread.sleep(1000);
        }

    }
}
