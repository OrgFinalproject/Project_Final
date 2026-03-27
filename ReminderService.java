package soft;

import java.util.List;

/**
 * Service responsible for sending appointment reminders.
 * 
 * This class processes a list of appointments,
 * generates reminder messages, and sends them
 * using a NotificationService.
 * 
 * Demonstrates Dependency Injection and loose coupling,
 * allowing different notification implementations to be used.
 * 
 * @author Nadeen Abu Aqel
 * @version 1.1
 */
public class ReminderService {

    /** Notification service used to send messages */
    private NotificationService notificationService;

    /**
     * Constructor to inject the notification service.
     *
     * @param notificationService the service used to send messages
     */
    public ReminderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Sends reminders for all given appointments.
     * 
     * For each appointment:
     * - Retrieves client email
     * - Creates a reminder message
     * - Sends the message using NotificationService
     *
     * @param appointments list of appointments
     */
    public void sendReminders(List<Appointment> appointments) {

        for (Appointment app : appointments) {
/**
 * Updated in Sprint 3:
 * - Fixed method calls (getOwner, getTimeSlot)
 */
            String email = app.getOwner().getEmail();

            String message = "Reminder: You have an appointment on "
                    + app.getDate() + " at "
                    + app.getTimeSlot().getStartTime();

            notificationService.send(email, message);
        }
    }
}
