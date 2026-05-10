
package soft;

/**
 * Business rule for validating ASSESSMENT appointments.
 * Ensures that assessment appointments follow strict constraints:
 * duration must be between 30 and 60 minutes,
 * and the number of participants must be exactly 1.
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public class AssessmentRule implements BookingRuleStrategy {

    /**
     * Validates whether an appointment satisfies the assessment rules.
     *
     * @param app the appointment to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() != AppointmentType.ASSESSMENT) {
            return true;
        }

        if (app.getDuration() < 30 || app.getDuration() > 60) {
            System.out.println("Assessment appointment must be between 30 and 60 minutes");
            return false;
        }

        if (app.getParticipants() != 1) {
            System.out.println("Assessment appointment must have exactly 1 participant");
            return false;
        }

        return true;
    }
}
