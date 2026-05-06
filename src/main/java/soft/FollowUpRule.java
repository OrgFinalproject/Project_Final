package soft;

public class FollowUpRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {
        
        if (app.getType()==AppointmentType.FOLLOW_UP) { 
        
         
        if (app.getDuration() > 20) {
            System.out.println("Follow-up appointment must be <= 20 minutes");
            return false;
        }

        if (app.getParticipants() != 1) {
            System.out.println("Follow-up appointment must have exactly 1 participant");
            return false;
        }
        
        }
        return true;

    
}}
