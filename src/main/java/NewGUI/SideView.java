package NewGUI;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideView extends MyView{

    GLRender listener;
    static FPSAnimator animator = null;
    float speed = 0.2f;

    /**���췽��<p>
     *
     * ���ô����С<p>
     * ��ӹر��¼�<p>
     * ����GLCapabilities��<p>
     * ����GLCanvas�࣬����<p>
     * GLCanvas�������GLEventListener<p>
     * ���������GLCanvas����<p>
     * ΪGLCanvas����ʵ����FPSAnimator�����߳�<p>
     * ���д���<p>
     *
     */
    public SideView() {
        super("Side View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GLCapabilities glcaps = new GLCapabilities(null);
        GLCanvas canvas = new GLCanvas(glcaps);
        listener = new SideViewRender();

        canvas.addGLEventListener(listener);
        //canvas.addMouseListener(listener);
        //this.addKeyListener((KeyListener) new KeyMonitor());

        getContentPane().add(canvas, BorderLayout.CENTER);
        //this.addMouseListener(new mouseEvent());
        animator = new FPSAnimator(canvas, 60, true);
        centerWindow(this);
//        this.setVisible(true);
//        animator.start();
    }

    public void run() {
        final SideView app = new SideView();
        // ��ʾ����
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                app.setVisible(true);
            }
        });
        // �����߳̿�ʼ
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                animator.start();
            }
        });
    }
}
