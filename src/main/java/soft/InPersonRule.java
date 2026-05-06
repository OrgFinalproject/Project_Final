package soft;

public class InPersonRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.IN_PERSON) {
         

        if (app.getParticipants() > 5) {
            System.out.println("In-person appointment cannot exceed 5 participants");
            return false;
        }
        }
        return true;
    }
}
