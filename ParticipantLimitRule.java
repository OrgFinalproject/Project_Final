package soft;

public class ParticipantLimitRule implements BookingRuleStrategy {

    private int maxParticipants;

    public ParticipantLimitRule(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public boolean isValid(Appointment app) {

        return app.getParticipants() <= maxParticipants;

    }
}