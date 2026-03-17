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

    public boolean bookAppointment(Appointment appointment,TimeSlot timeslot) {
        for (BookingRuleStrategy rule : rules) {
            if (!rule.isValid(appointment)) {
                System.out.println("Booking rejected: rule violated"+"\n");
                return false;
            }
        }
        
        if(appointment.getDuration()>timeslot.getRemainingTime())
        {
        	  System.out.println("Booking rejected:not enough time"+"\n");
        	  return false;
        }
        appointment.setStatus("Confirmed");
        timeslot.addAppointment(appointment);
        System.out.println("Appointment booked successfully!");
        
        return true;
    }
}
