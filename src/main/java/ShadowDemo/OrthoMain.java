package ShadowDemo;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OrthoMain extends JFrame {

    GLRender listener = new GLRender();
    static FPSAnimator animator = null;

    public OrthoMain() throws HeadlessException {
        super("��ͶӰ,�������ҵ����Ƕ�");
        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glcaps = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(glcaps);
        canvas.addGLEventListener(listener);
        this.addKeyListener(new keyEventListener());
        //canvas.addMouseListener(listener);
        getContentPane().add(canvas, BorderLayout.CENTER);
        animator = new FPSAnimator(canvas, 60, true);

        centerWindow(this);
    }

    private void centerWindow(Component frame) { // ���д���
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

    public static void main(String[] args) {
        final OrthoMain app = new OrthoMain();
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

    class keyEventListener implements KeyListener {

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                listener.xRot -= 5.0f;
            }

            if (e.getKeyCode() == KeyEvent.VK_R) {
                listener.xRot += 5.0f;
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                listener.yRot -= 5.0f;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                listener.yRot += 5.0f;
            }

            listener.xRot = (float) ((int) listener.xRot % 360);
            listener.yRot = (float) ((int) listener.yRot % 360);


        }

        public void keyReleased(KeyEvent e) {
        }
    }
}
