package soft;

public class GroupRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.GROUP) {
        

        if (app.getParticipants() <2) {
            System.out.println("Group appointment must have more than 1 participant");
            return false;
        }

        if (app.getDuration() < 60) {
            System.out.println("Group appointment must be at least 60 minutes");
            return false;
        }
        }  
        
        return true;
    }
}
