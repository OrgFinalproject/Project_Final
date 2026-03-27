package soft;

import java.util.List;

/**
 * Service responsible for sending appointment reminders.
 * 
 * This class processes a list of appointments,
 * generates reminder messages, and sends them
 * using a NotificationService.
 * 
 * @author Nadeen Abu Aqel
 * @version 1.0
 */
public class ReminderService {

    /**
     * Notification service used to send messages.
     */
    private NotificationService notificationService;

    /**
     * Constructor to inject notification service.
     *
     * @param notificationService the service used to send messages
     */
    public ReminderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Sends reminders for all appointments.
     *
     * @param appointments list of appointments
     */
    public void sendReminders(List<Appointment> appointments) {

        for (Appointment app : appointments) {

            String email = app.getClient().getEmail();

            String message = "Reminder: You have an appointment on "
                    + app.getDate() + " at " + app.getTime();

            notificationService.send(email, message);
        }
    }
}