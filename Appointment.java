
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

import java.time.LocalDateTime;

public class Appointment {

    private String date;
    private int duration;
    private int participants;
    private String status;
    private AppointmentType type;
    /*
     * @author : talajaber 
     * add client and add time slot
     */
    private Client owner;
    private TimeSlot timeSlot;


   

    public Appointment(String date, String time, int duration, int participants, AppointmentType type, Client owner) {
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.participants = participants;
        this.type = type;
        this.owner=owner;
        this.status = "Pending";
    }
	public Client getOwner() {
        return owner;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
    public boolean isFuture() {
        LocalDateTime appointmentDateTime = LocalDateTime.of(date,timeSlot.getStartTime());
        return appointmentDateTime.isAfter(LocalDateTime.now());
    }
    
    
    public String getDate() { return date; }
    public int getDuration() { return duration; }
    public int getParticipants() { return participants; }
    public AppointmentType getType() { return type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

     public void displayInfo() {
        System.out.println("=== تفاصيل الموعد ===");
        System.out.println("الزبون: " + client.getName());
        System.out.println("التاريخ: " + date);
        System.out.println("المدة: " + duration + " دقيقة");
        System.out.println("عدد المشاركين: " + participants);
        System.out.println("الحالة: " + status);
        System.out.println("====================");
    }
 }

