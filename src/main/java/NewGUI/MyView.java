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

    /**构造方法<p>
     *
     * 设置窗体大小<p>
     * 添加关闭事件<p>
     * 构造GLCapabilities类<p>
     * 构造GLCanvas类，对象化<p>
     * GLCanvas对象添加GLEventListener<p>
     * 给窗体添加GLCanvas对象<p>
     * 为GLCanvas对象实例化FPSAnimator动画线程<p>
     * 居中窗体<p>
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

    /** 居中窗体*/
    public void centerWindow(Component frame) { // 居中窗体
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
