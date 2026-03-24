
  package soft;
import java.util.ArrayList;
import java.util.List;
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


/*// Modified for Spring 5 compatibility
// Author tala-barhoush&tala-jaber*/

public BookingService() {
    rules = new ArrayList<>();

    // Sprint 2 rules
    rules.add(new DurationRule(30));
    rules.add(new ParticipantLimitRule(10));

    // Sprint 5 rules
    rules.add(new UrgentRule());
    rules.add(new GroupRule());
}
/*
 * -add modify and cancel appointment
 * - set time slot to the appointment 
 * -  Object requester to ensure that is company or user
 */

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
    appointment.setTimeSlot(timeslot);
    System.out.println("Appointment booked successfully!");
    
    return true;
}
    
    
    
    public boolean cancelAppointment(Appointment appointment, Object requester, TimeSlot timeslot) {

        if (appointment == null || requester == null || timeslot == null) {
            return false;
        }

        // Only future appointments
        if (!appointment.isFuture()) {
            System.out.println("لا يمكن إلغاء موعد قديم.");
            return false;
        }

        boolean isAdmin = requester instanceof Company;

        // If requester is Client, must be owner (unless admin)
        if (requester instanceof Client) {
            Client client = (Client) requester;

            if (!appointment.getOwner().equals(client) && !isAdmin) {
                System.out.println("لا يمكنك إلغاء موعد ليس لك.");
                return false;
            }
        }

        // Remove from TimeSlot
        timeslot.removeAppointment(appointment);

        // Update status
        appointment.setStatus("Cancelled");

        System.out.println("تم إلغاء الموعد بنجاح.");
        return true;
    }
    
   /*****/
    
    public boolean modifyAppointment(Appointment appointment,TimeSlot newSlot, Object requester) {

    	 TimeSlot oldSlot;
    	oldSlot = appointment.getTimeSlot();
        if (appointment == null || newSlot == null || requester == null) {
            return false;
        }

        // Only future appointments
        if (!appointment.isFuture()) {
            System.out.println("لا يمكن تعديل موعد قديم.");
            return false;
        }

        boolean isAdmin = requester instanceof Company; // حسب كلاس الادمن عندك

        // Authorization check
        if (requester instanceof Client) {
            Client client = (Client) requester;

            if (!appointment.getOwner().equals(client) && !isAdmin) {
                System.out.println("لا يمكنك تعديل موعد ليس لك.");
                return false;
            }
        }

        // Remove from old slot
        oldSlot.removeAppointment(appointment);

        // Check remaining time in new slot
        if (appointment.getDuration() > newSlot.getRemainingTime()) {
            // rollback
            oldSlot.addAppointment(appointment);
            System.out.println("Not enough remaining time in new slot.");
            return false;
        }

        // Add to new slot
        newSlot.addAppointment(appointment);

        // Update reference (مهم جداً)
        appointment.setTimeSlot(newSlot);

        System.out.println("Appointment modified (rescheduled) successfully.");
        return true;
    }
    
    
    
    
    
    
}
