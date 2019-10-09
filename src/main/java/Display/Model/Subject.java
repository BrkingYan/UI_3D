package Display.Model;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers1(String s);
    void notifyObservers2(float roll_degree,float pit_degree);
}
