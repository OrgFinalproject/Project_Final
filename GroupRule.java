package soft;

public class GroupRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {
        if (app.getType() == AppointmentType.GROUP) {
            return app.getParticipants() <= 20;
        }
        return true;
    }
}
