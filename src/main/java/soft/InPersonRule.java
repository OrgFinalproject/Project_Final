package soft;

/**
 * InPersonRule validates booking constraints for IN_PERSON appointments.
 * It ensures that in-person meetings respect the maximum allowed number
 * of participants for physical attendance.
 *
 * Rule:
 * - Maximum 5 participants allowed
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public class InPersonRule implements BookingRuleStrategy {

    /**
     * Validates whether the appointment satisfies IN_PERSON booking rules.
     *
     * @param app the appointment to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.IN_PERSON) {

            if (app.getParticipants() > 5) {
                System.out.println("In-person appointment cannot exceed 5 participants");
                return false;
            }
        }

        return true;
    }
}
