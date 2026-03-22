
  package soft;

import java.util.*;
/*  edited by tala-barhoush*/
public class BookingService {

    private Map<AppointmentType, List<BookingRuleStrategy>> rulesMap;

    public BookingService() {
        rulesMap = new HashMap<>();

        // URGENT rules
        List<BookingRuleStrategy> urgentRules = new ArrayList<>();
        urgentRules.add(new DurationRule(30));  // general
        urgentRules.add(new ParticipantLimitRule(2)); // general
        urgentRules.add(new UrgentRule());  // specific
        rulesMap.put(AppointmentType.URGENT, urgentRules);

        // GROUP rules
        List<BookingRuleStrategy> groupRules = new ArrayList<>();
        groupRules.add(new DurationRule(120)); // example max duration
        groupRules.add(new ParticipantLimitRule(20));
        groupRules.add(new GroupRule());
        rulesMap.put(AppointmentType.GROUP, groupRules);

        // VIRTUAL rules
        List<BookingRuleStrategy> virtualRules = new ArrayList<>();
        virtualRules.add(new VirtualRule());
        rulesMap.put(AppointmentType.VIRTUAL, virtualRules);
    }

    public boolean bookAppointment(Appointment app, TimeSlot timeslot) {

        List<BookingRuleStrategy> applicableRules = rulesMap.get(app.getType());
        if (applicableRules == null) {
            System.out.println("No rules defined for this type");
            return false;
        }

        for (BookingRuleStrategy rule : applicableRules) {
            if (!rule.isValid(app)) {
                System.out.println("Booking rejected: rule violated\n");
                return false;
            }
        }

        if (app.getDuration() > timeslot.getRemainingTime()) {
            System.out.println("Booking rejected: not enough time\n");
            return false;
        }

        app.setStatus("Confirmed");
        timeslot.addAppointment(app);
        System.out.println("Appointment booked successfully!");
        return true;
    }
}
