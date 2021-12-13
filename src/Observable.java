import javax.print.attribute.standard.PrinterState;
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
    public void notifyObservers(IPrinterState state)
    {
        observers.forEach(observer-> observer.update(state) );
    }

}
