package D3_Scene;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThirdExp extends JFrame {

    Render2 listener;
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
    public ThirdExp() {
        super("��������,��ӭ��½�ҵĿռ� http://wjyjimy.iteye.com/");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GLCapabilities glcaps = new GLCapabilities(null);
        GLCanvas canvas = new GLCanvas(glcaps);
        listener = new Render2();

        canvas.addGLEventListener(listener);
        //canvas.addMouseListener(listener);
        this.addKeyListener((KeyListener) new KeyMonitor());

        getContentPane().add(canvas, BorderLayout.CENTER);
        this.addMouseListener(new mouseEvent());
        animator = new FPSAnimator(canvas, 60, true);
        centerWindow(this);
//        this.setVisible(true);
//        animator.start();


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

    public static void main(String[] args) {
        final ThirdExp app = new ThirdExp();
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

    private class KeyMonitor extends KeyAdapter {

        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            //System.out.println(key);
            if (key == KeyEvent.VK_RIGHT) {
            }
            if (key == KeyEvent.VK_LEFT) {
            }
            if (key == KeyEvent.VK_UP) {
            }
            if (key == KeyEvent.VK_DOWN) {
            }
            if (key == KeyEvent.VK_SHIFT) {
                speed = 0.2f;
            }

        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            // System.out.println(key);
            if (key == KeyEvent.VK_RIGHT) {
                listener.m_bsipic.g_Angle += speed * 4;
                System.out.println("right");
            }
            if (key == KeyEvent.VK_LEFT) {
                listener.m_bsipic.g_Angle -= speed * 4;//��ת
                System.out.println("left");
            }
            if (key == KeyEvent.VK_UP) {
                double sin = Math.sin(listener.m_bsipic.rad_xz) * speed;
                double cos = Math.cos(listener.m_bsipic.rad_xz) * speed;
                listener.m_bsipic.g_eye[2] += sin;
                listener.m_bsipic.g_eye[0] += cos;
                System.out.println("up");
            }
            if (key == KeyEvent.VK_DOWN) {
                double sin = Math.sin(listener.m_bsipic.rad_xz) * speed;
                double cos = Math.cos(listener.m_bsipic.rad_xz) * speed;
                listener.m_bsipic.g_eye[2] -= sin;
                listener.m_bsipic.g_eye[0] -= cos;
                System.out.println("down");
            }
            if (key == KeyEvent.VK_SHIFT) {
                speed = speed * 4;
                System.out.println("shift");
            }

            /*if (key == 32) {
                if (listener.isspacepress == false) {
                    listener.isspacepress = true;
                    listener.m_bsipic.x=0;
                    listener.m_bsipic.y=0;

                } else {
                    listener.isspacepress = false;
                }

            }
            if (key == 33) {
                listener.m_bsipic.g_elev += 0.2f;
            }
            if (key == 34) {
                listener.m_bsipic.g_elev -= 0.2f;
            }

            //�ƶ���Ůͼ
            if (key == 87) {
                listener.m_bsipic.y += 0.001f;
                System.out.println(listener.m_bsipic.y);

            }
            if (key == 65) {
                listener.m_bsipic.x -= 0.001f;
                System.out.println(listener.m_bsipic.x);
            }
            if (key == 83) {
                listener.m_bsipic.y -= 0.001f;
                System.out.println(listener.m_bsipic.y);
            }
            if (key == 68) {
                listener.m_bsipic.x += 0.001f;
                System.out.println(listener.m_bsipic.x);
            }*/

        }
    }

    class mouseEvent implements MouseListener, MouseMotionListener {

        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            System.out.println(e);

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }
    }
}
