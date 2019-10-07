package NewGUI;

public class Main {
    public static void main(String[] args) {
        new Thread(new TopView()).start();
        new Thread(new SideView()).start();
        new Thread(new StereoView()).start();
    }
}
