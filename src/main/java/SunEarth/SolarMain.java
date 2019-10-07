package SunEarth;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

import javax.swing.*;
import java.awt.*;

public class SolarMain extends JFrame {
    GLRender listener=new GLRender();
    static FPSAnimator animator=null;
    public SolarMain() throws HeadlessException {
        super("̫�������");
        setSize(600,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GLCapabilities glcaps=new GLCapabilities(null);          //�����֮ǰ�½ڵĴ���������.
        GLCanvas canvas=new GLCanvas(glcaps);
        canvas.addGLEventListener((GLEventListener) listener);
        //canvas.addMouseListener(listener);
        getContentPane().add(canvas, BorderLayout.CENTER);
        animator=new FPSAnimator(canvas,60,true);

        centerWindow(this);
    }
    private void centerWindow(Component frame) { // ���д���
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        frame.setLocation((screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1);

    }

    public static void main(String[] args) {
        final SolarMain app = new SolarMain();
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
