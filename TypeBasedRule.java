package Software;

/**
 * قاعدة تعتمد على نوع الموعد (Sprint 5)
 * تطبق سلوك مختلف حسب نوع الموعد
 */
public class TypeBasedRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {

        switch (appointment.getType()) {

            case URGENT:
                System.out.println("Urgent appointment: priority processing");
                break;

            case GROUP:
                System.out.println("Group appointment: special handling required");
                break;

            case VIRTUAL:
                System.out.println("Virtual appointment: no physical room needed");
                break;

            default:
                System.out.println("Standard appointment");
        }

        return true; // ما نمنع الحجز، فقط نطبق سلوك مختلف
    }
}
