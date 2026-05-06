package soft;

public class VirtualRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.VIRTUAL) {
        

        if (app.getParticipants()> 8) {
            System.out.println("Virtual appointment cannot exceed 10 participants");
            return false;
        }
    }
        return true;
    }
}
