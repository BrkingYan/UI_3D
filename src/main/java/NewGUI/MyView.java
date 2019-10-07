package NewGUI;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyView extends JFrame implements Runnable {


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
    public MyView(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GLCapabilities glcaps = new GLCapabilities(null);
        GLCanvas canvas = new GLCanvas(glcaps);
        listener = new GLRender();

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

    protected void init(){

    }

    /** ���д���*/
    public void centerWindow(Component frame) { // ���д���
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        frame.setLocation((screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1);
    }

    public void run() {

    }
}
