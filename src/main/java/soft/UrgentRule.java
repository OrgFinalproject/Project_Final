package soft;

import java.time.LocalDate;

/**
 * This class represents the validation rule for urgent appointments.
 * It checks whether the appointment duration and number of participants
 * satisfy the allowed conditions for urgent appointments.
 * 
 * @author Nadeen and Tala Barhoush
 * @version 1.0
 */
public class UrgentRule implements BookingRuleStrategy {

    /**
     * Validates the given appointment.
     * 
     * @param app the appointment to validate
     * @return true if the appointment is valid, otherwise false
     */
    @Override
    public boolean isValid(Appointment app) {

        // Check if the appointment type is urgent
        if (app.getType() == AppointmentType.URGENT) {

            // Ensure the appointment duration does not exceed 30 minutes
            if (app.getDuration() > 30) {
                System.out.println("Urgent appointment must be <= 30 minutes");
                return false;
            }

            // Ensure the number of participants does not exceed 2
            if (app.getParticipants() > 2) {
                System.out.println("Urgent appointment max 2 participants");
                return false;
            }
        }

        // Return true if all validation rules are satisfied
        return true;
    }
}