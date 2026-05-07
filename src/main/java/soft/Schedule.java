package soft;

import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule {
	
	private long id;
	private LocalDate date ;
	private ArrayList <TimeSlot> timeSlots ;
	
	Schedule (long id,LocalDate date)
	{
		this.id=id;
		this.date=date;
		this.timeSlots=new ArrayList <>();
	}
	
	 public void addTimeSlot(TimeSlot slot) {
	        timeSlots.add(slot);
	   }
	 public void setDate(LocalDate date) {
		 this.date=date;
	 }
	 
	public long getId() {
		return this.id;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public ArrayList<TimeSlot> getAllTimeSlots() 
	{
		 return timeSlots;
	}
	
	public boolean removeTimeSlot(TimeSlot slot) {
	    return timeSlots.remove(slot);
	}
	
	public boolean hasTimeSlot(TimeSlot slot) {
	    return timeSlots.contains(slot);
	}

	
}
