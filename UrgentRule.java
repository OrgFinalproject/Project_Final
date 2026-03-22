package soft;

public class UrgentRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment app) {
        if (app.getType() == AppointmentType.URGENT) {
            return true;
        }
        return true;
    }
}
