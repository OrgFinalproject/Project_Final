package soft;
/*
 * @author Nadeen
 * @version 1.
 */
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyUser(Client user, String message) {
        for (Observer obj : observers) {
            obj.update(user, message);
        }
    }
}
