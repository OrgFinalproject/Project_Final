package soft;

/**
 * GroupRule validates booking constraints for GROUP appointments.
 * It ensures that group meetings meet minimum requirements for participants
 * and duration.
 *
 * Rules:
 * - Must have at least 2 participants
 * - Duration must be at least 60 minutes
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public class GroupRule implements BookingRuleStrategy {

    /**
     * Validates whether the appointment satisfies GROUP booking rules.
     *
     * @param app the appointment to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.GROUP) {

            if (app.getParticipants() < 2) {
                System.out.println("Group appointment must have more than 1 participant");
                return false;
            }

            if (app.getDuration() < 60) {
                System.out.println("Group appointment must be at least 60 minutes");
                return false;
            }
        }

        return true;
    }
}