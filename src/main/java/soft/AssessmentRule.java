package soft;

public class AssessmentRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.ASSESSMENT) {
           
        

        if (app.getDuration() < 30 || app.getDuration() > 60) {
            System.out.println("Assessment appointment must be between 30 and 60 minutes");
            return false;
        }

        if (app.getParticipants() != 1) {
            System.out.println("Assessment appointment must have exactly 1 participant");
            return false;
        }
        }
        return true;
    }
}
