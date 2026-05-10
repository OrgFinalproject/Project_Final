package soft;

/**
 * Represents an observer in the Observer design pattern.
 * Classes implementing this interface will receive notifications
 * when an event occurs in the system (e.g., appointment updates).
 *
 * @author Nadeen
 * @version 1.0
 */
public interface Observer {

    /**
     * Receives an update/notification from the subject.
     *
     * @param user the client receiving the notification
     * @param message the notification message content
     */
    void update(Client user, String message);
}