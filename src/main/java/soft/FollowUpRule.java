package soft;

/**
 * FollowUpRule validates booking rules for FOLLOW_UP appointments.
 * It ensures that follow-up appointments comply with business constraints
 * such as limited duration and single participant requirement.
 *
 * Rules:
 * - Duration must be 20 minutes or less
 * - Must have exactly 1 participant
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public class FollowUpRule implements BookingRuleStrategy {

    /**
     * Validates whether the given appointment satisfies FOLLOW_UP rules.
     *
     * @param app the appointment to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.FOLLOW_UP) {

            if (app.getDuration() > 20) {
                System.out.println("Follow-up appointment must be <= 20 minutes");
                return false;
            }

            if (app.getParticipants() != 1) {
                System.out.println("Follow-up appointment must have exactly 1 participant");
                return false;
            }
        }

        return true;
    }
}