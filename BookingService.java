package soft;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private List<BookingRuleStrategy> rules;
    public BookingService() {
    rules = new ArrayList<>();
    rules.add(new DurationRule(30));
    rules.add(new ParticipantLimitRule(10));

    }

    public boolean bookAppointment(Appointment appointment) {
        for (BookingRuleStrategy rule : rules) {
            if (!rule.isValid(appointment)) {
                System.out.println("Booking rejected: rule violated");
                return false;
            }
        }

        appointment.setStatus("Confirmed");
        System.out.println("Appointment booked successfully!");

        return true;
    }
}
