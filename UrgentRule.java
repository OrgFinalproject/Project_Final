package soft;

public class UrgentRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {

        if (app.getDuration() > 30) {
            System.out.println("Urgent appointment must be <= 30 minutes");
            return false;
        }

        if (app.getParticipants() > 2) {
            System.out.println("Urgent appointment max 2 participants");
            return false;
        }

        return true;
    }
}
