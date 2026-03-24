package soft;

/**
 * Interface for sending notifications.
 * 
 * This interface defines a contract for sending messages
 * to users (e.g., email, SMS, or mock).
 * 
 * @author Nadeen Abu Aqel
 * @version 1.0
 */
public interface NotificationService {

    /**
     * Sends a notification message.
     *
     * @param to recipient (email or identifier)
     * @param message message content
     */
    void send(String to, String message);
}