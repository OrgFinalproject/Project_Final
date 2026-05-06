package soft;

import java.util.ArrayList;


import java.util.List;
import java.time.LocalDate;
import java.util.*;
/*  edited by tala-Jaber*/
public class BookingService {
	private List<BookingRuleStrategy> rules;
   private int maxDuration;
   private int maxParticipants;
  
/*// Modified for Spring 5 compatibility
// Author tala-barhoush&tala-jaber*/
   
/*
 * @param maxParticipants 
 * @param maxDuration
 */
public BookingService(int maxParticipants,int maxDuration) {
	  rules = new ArrayList<BookingRuleStrategy>();
     this.maxDuration=maxDuration;
      this.maxParticipants=maxParticipants;
    		  
    // Sprint 2 rules
    rules.add(new DurationRule(this.maxDuration));
    rules.add(new ParticipantLimitRule(this.maxParticipants));
   //sprint 5 rules
    rules.add(new TypeBasedRule());
rules.add(new UrgentRule());
 rules.add(new GroupRule());
    rules.add(new IndividualRule());
    rules.add(new VirtualRule());
    rules.add(new InPersonRule());
    rules.add(new FollowUpRule());
    rules.add(new AssessmentRule());
}
/*
 * -add modify and cancel appointment
 * - set time slot to the appointment 
 * -  Object requester to ensure that is company or user
 */

public boolean bookAppointment(Appointment appointment,TimeSlot timeslot) {
	if(appointment.getDate()==null )
	{
		  System.out.println("Booking rejected:cannot book without object date"+"\n");
		return false;
	}
	
	if(appointment.getOwner()==null )
	{
        System.out.println("Booking rejected:cannot book without object Client"+"\n");
		return false;
		}	
	if(appointment.getDate().isBefore(LocalDate.now()))
	{
		  System.out.println("Booking rejected: cannot book in old date"+"\n");
		return false;
	}
	
	
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
    
    

    
    public boolean cancelAppointment(Appointment appointment, Object requester) {

        if (appointment == null || requester == null) {
            return false;
        }

        // Only future appointments
        if (!appointment.isFuture()) {
        	System.out.println("Cannot cancel a past appointment.\n");
            return false;
        }

        boolean isAdmin = requester instanceof Company;

        // If requester is Client, must be owner (unless admin)
        if (requester instanceof Client) {
            Client client = (Client) requester;

            if (!appointment.getOwner().equals(client) && !isAdmin) {
            	System.out.println("You cannot cancel an appointment that is not yours.");
                return false;
            }
        }

        // Remove from TimeSlot
        appointment.getTimeSlot().removeAppointment(appointment);
         appointment.setTimeSlot(null);
        // Update status
        appointment.setStatus("Cancelled");

        System.out.println("Appointment cancelled successfully.");
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
        	System.out.println("Past appointments cannot be modified.");
            return false;
        }

        boolean isAdmin = requester instanceof Company; 
        // Authorization check
        if (requester instanceof Client) {
            Client client = (Client) requester;

            if (!appointment.getOwner().equals(client) && !isAdmin) {
            	System.out.println("You cannot modify an appointment that is not yours.");
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

        // Update reference 
        appointment.setTimeSlot(newSlot);

        System.out.println("Appointment modified (rescheduled) successfully.");
        return true;
    }
    
      
    
    
}
