package soft;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of NotificationService.
 * 
 * This class simulates sending notifications without actually
 * sending real emails or messages.
 * 
 * It records all sent messages in a list for testing purposes.
 * 
 * @author Nadeen Abu Aqel
 * @version 1.0
 */
public class MockNotificationService implements NotificationService {

    /**
     * List to store all sent messages for verification in tests.
     */
    private List<String> sentMessages = new ArrayList<>();

    /**
     * Simulates sending a notification.
     * Instead of sending a real message, it stores the message
     * in a list and prints it to the console.
     *
     * @param to the recipient's email address
     * @param message the message content
     */
    @Override
    public void send(String to, String message) {

        String record = "To: " + to + " | Message: " + message;

        sentMessages.add(record);

        System.out.println("Mock Send -> " + record);
    }

    /**
     * Returns all recorded messages that were "sent".
     *
     * @return list of sent messages
     */
    public List<String> getSentMessages() {
        return sentMessages;
    }
}