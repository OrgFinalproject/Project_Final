package soft;

/**
 * Rule that enforces a maximum number of participants per appointment.
 * Implements {@link BookingRuleStrategy} as part of the Strategy design pattern.
 *
 * @author Nadeen
 * @version 1.0
 */
public class ParticipantLimitRule implements BookingRuleStrategy {

    /** Maximum allowed number of participants */
    private int maxParticipants;

    /**
     * Constructs the rule with a defined participant limit.
     *
     * @param maxParticipants the maximum number of allowed participants
     */
    public ParticipantLimitRule(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    /**
     * Validates whether the appointment satisfies the participant limit.
     *
     * @param app the appointment to validate
     * @return true if participants are within the allowed limit, false otherwise
     */
    @Override
    public boolean isValid(Appointment app) {
        return app.getParticipants() <= maxParticipants;
    }
}