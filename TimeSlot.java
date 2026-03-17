
package software;


import java.time.LocalTime;
import java.util.List;

import soft.Appointment;

public class TimeSlot {
	
	private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;
    private List< Appointment>  Appointments;
    
    public TimeSlot(LocalTime startTime, LocalTime endTime) {
    	  if (endTime.isBefore(startTime)) {
    	        throw new IllegalArgumentException("The end time should be after the start time!");
    	    }
		this.startTime = startTime;
        this.endTime = endTime;
      this.isAvailable = true;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }
       
    
    public boolean addAppointment(Appointment appointment)
    {
    	Appointments.add(appointment);
    	return true;
    }
    public long getRemainingTime() {
        long totalBookedMinutes = Appointments.stream()
                                               .mapToLong(Appointment::getDuration)
                                               .sum();
        return java.time.Duration.between(startTime, endTime).toMinutes() - totalBookedMinutes;
    }
    public void book() {
        if (isAvailable) {
            isAvailable = false;
        } else {
        	 throw new IllegalArgumentException("this time slot is already Booked");
        }
    }
    
    public void unbook() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
        	 throw new IllegalArgumentException("this time slot is already unBooked");
        }
    }

}