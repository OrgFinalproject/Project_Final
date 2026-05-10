package soft;

public class VirtualRule implements BookingRuleStrategy {
	 /**
     * Validates the given appointment.
     * 
     * @param app the appointment to validate
     * @return true if the appointment is valid, otherwise false
     */
    @Override
    public boolean isValid(Appointment app) {

        if (app.getType() == AppointmentType.VIRTUAL && app.getParticipants() > 10) {
            System.out.println("Virtual appointment cannot exceed 10 participants");
            return false;
        }

        return true;
    }
}
