import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import RefDemos.CubeTexture;

import javax.swing.*;

public abstract class Painter extends JPanel implements Observer {
    //初始化并启动
    public void init(){
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        CubeTexture r = new CubeTexture();
        glcanvas.addGLEventListener(r);
        glcanvas.setSize(500, 500);
        this.add(glcanvas);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true );
        animator.start();
    }
}
