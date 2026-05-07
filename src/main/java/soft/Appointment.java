
/**
 * تمثل هذه الفئة موعداً (Appointment) يتم إنشاؤه بواسطة الزبون.
 * تحتوي على تفاصيل الحجز مثل الوقت، المدة، وعدد المشاركين.
 * * @author Nadeen & Tala Barhoush
 * @version 2.0 
 */
/**
 * Updated in Sprint 5:
** @author Tala
 * - Added support for multiple appointment types
 * - Integrated with rule-based system for flexible business logic
 */
package soft;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {

    private LocalDate date;

	
    private int duration;
    private int participants;
    private String status;
    private AppointmentType type;
    /*
     * @author : talajaber 
     * add client and add time slot
     */
    private Client client;
    private TimeSlot timeSlot;


   

    public Appointment(LocalDate date, int duration, int participants, AppointmentType type, Client client){
        this.date = date;
        this.duration = duration;
        this.participants = participants;
        this.type = type;
        this.client=client;
        this.status = "Pending";
    }
	public Client getOwner()  {
        return client;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
     public boolean isFuture() {
        if (date == null || timeSlot == null || timeSlot.getStartTime() == null) {
            return false;
        }

        LocalDateTime appointmentDateTime =
                LocalDateTime.of(date, timeSlot.getStartTime());

        return appointmentDateTime.isAfter(LocalDateTime.now());
    }
    
    
    
    public LocalDate getDate() { return date; }
    public int getDuration() { return duration; }
    public int getParticipants() { return participants; }
    public AppointmentType getType() { return type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

     public void displayInfo() {
        System.out.println("= تفاصيل الموعد =");
        System.out.println("الزبون: " + client.getName());
        System.out.println("التاريخ: " + date);
        System.out.println("المدة: " + duration + " دقيقة");
        System.out.println("عدد المشاركين: " + participants);
        System.out.println("الحالة: " + status);
        System.out.println("====================");
    }
 }

