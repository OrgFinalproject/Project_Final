package soft;

import java.util.List;
import java.time.LocalDateTime;

/**
 * Service responsible for sending appointment reminders.
 * @author Nadeen Abu Aqel
 version 1.1
 */
public class ReminderService {

    private NotificationService notificationService;

    public ReminderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendReminders(List<Appointment> appointments) {
        LocalDateTime now = LocalDateTime.now();

        for (Appointment app : appointments) {
            if (app.getDate() != null && app.getTimeSlot() != null) {
                
                LocalDateTime apptTime = LocalDateTime.of(
                        app.getDate(),
                        app.getTimeSlot().getStartTime()
                );

                if (apptTime.isAfter(now) && apptTime.isBefore(now.plusHours(24))) {
                    Client client = app.getOwner();
                    String message = "Reminder: Dear " + client.getName() + 
                                     ", you have an appointment on " + apptTime;

                    notificationService.notifyUser(client, message);
                }
            }
        }
    }
}
