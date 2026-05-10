package soft;

/**
 * IndividualRule validates booking constraints for INDIVIDUAL appointments.
 * It ensures that individual appointments follow strict business rules
 * regarding the number of participants.
 *
 * Rules:
 * - Must have exactly 1 participant
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public class IndividualRule implements BookingRuleStrategy {

    /**
     * Validates whether the appointment satisfies INDIVIDUAL booking rules.
     *
     * @param app the appointment to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.INDIVIDUAL) {

            if (app.getParticipants() != 1) {
                System.out.println("Individual appointment must have exactly 1 participant");
                return false;
            }
        }

        return true;
    }
}