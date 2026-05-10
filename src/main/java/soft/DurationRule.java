package soft;

/**
 * DurationRule is a booking validation rule that checks
 * whether an appointment duration does not exceed a defined maximum limit.
 *
 * This rule is part of the rule-based booking system used to validate
 * appointments before confirming a booking.
 *
 * @author Nadeen 
 * @author  Tala Jaber
 * @version 1.0
 */
public class DurationRule implements BookingRuleStrategy {

    /** Maximum allowed duration for an appointment (in minutes) */
    private int maxDuration;

    /**
     * Constructs a DurationRule with a specific maximum duration.
     *
     * @param max the maximum allowed duration in minutes
     */
    public DurationRule(int max) {
        this.maxDuration = max;
    }

    /**
     * Validates whether the appointment duration is within the allowed limit.
     *
     * @param app the appointment to validate
     * @return true if duration is less than or equal to maxDuration, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {
        return app.getDuration() <= maxDuration;
    }
}