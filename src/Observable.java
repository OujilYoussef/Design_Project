import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    List<Observer> observers=new ArrayList<>();
    public void register(Observer observer)
    {
        observers.add(observer);
    }
    public void unregister(Observer observer)
    {
        observers.remove(observer);
    }
    public void notifyObservers()
    {
        observers.forEach(observer-> observer.update(2) );
    }

}
