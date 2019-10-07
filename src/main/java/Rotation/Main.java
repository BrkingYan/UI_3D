package Rotation;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class Main {
    public static void main( String[] args ) {
        final GLProfile profile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities capabilities = new GLCapabilities( profile );
        // The canvas
        final GLCanvas glcanvas = new GLCanvas( capabilities );
        Cube cube = new Cube();
        glcanvas.addGLEventListener( cube );
        glcanvas.setSize( 400, 400 );
        final JFrame frame = new JFrame ( " Multicolored cube" );
        frame.getContentPane().add( glcanvas );
        frame.setSize( frame.getContentPane().getPreferredSize() );
        frame.setVisible( true );
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);
        animator.start();
    }
}
