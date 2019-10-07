package NewGUI;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopView extends MyView{

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
    public TopView() {
        super("Top View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GLCapabilities glcaps = new GLCapabilities(null);
        GLCanvas canvas = new GLCanvas(glcaps);
        listener = new TopViewRender();

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
        final TopView app = new TopView();
        // 显示窗体
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                app.setVisible(true);
            }
        });
        // 动画线程开始
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                animator.start();
            }
        });
    }
}
