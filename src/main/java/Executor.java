import com.jogamp.opengl.GLEventListener;

public class Executor implements Runnable {
    private Painter painter;

    public Executor(Painter painter){
        this.painter = painter;
    }

    public void run() {
        if (painter instanceof GLEventListener){
            painter.init();
        }
        System.out.println("Painter Run");
    }
}
