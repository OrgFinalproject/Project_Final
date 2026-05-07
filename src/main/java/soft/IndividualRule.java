package soft;

public class IndividualRule implements BookingRuleStrategy {

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
