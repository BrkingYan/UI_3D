package Display.Model;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers();
}
