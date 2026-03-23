package soft;

public class GroupRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {

        if (app.getParticipants() < 2) {
            System.out.println("Group appointment must have at least 2 participants");
            return false;
        }

        if (app.getParticipants() > 20) {
            System.out.println("Group appointment cannot exceed 20 participants");
            return false;
        }

        if (app.getDuration() < 60) {
            System.out.println("Group appointment must be at least 60 minutes");
            return false;
        }

        return true;
    }
}
