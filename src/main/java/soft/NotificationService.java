package soft;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for managing and sending notifications to users.
 * Implements the Observer pattern to notify all registered observers
 * when an event occurs (e.g., appointment confirmation).
 *
 * @author Nadeen
 * @version 1.0
 */
public class NotificationService {

    /** List of registered observers who receive notifications */
    private List<Observer> observers = new ArrayList<>();

    /**
     * Registers a new observer to receive notifications.
     *
     * @param observer the observer to be added
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Sends a notification message to all registered observers.
     *
     * @param user the client who will receive the notification
     * @param message the message content to be sent
     */
    public void notifyUser(Client user, String message) {
        for (Observer obj : observers) {
            obj.update(user, message);
        }
    }
}