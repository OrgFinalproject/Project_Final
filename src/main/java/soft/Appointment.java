package soft;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * تمثل هذه الفئة موعداً (Appointment) يتم إنشاؤه بواسطة الزبون.
 * تحتوي على تفاصيل الحجز مثل الوقت، المدة، وعدد المشاركين.
 *
 * Updated in Sprint 5:
 * - دعم أنواع متعددة من المواعيد
 * - دمج نظام القواعد (Rule-based system)
 *
 * @author Nadeen and Tala Barhoush
 * @version 2.0
 */
public class Appointment {

    private LocalDate date;
    private int duration;
    private int participants;
    private String status;
    private AppointmentType type;

    private Client client;
    private TimeSlot timeSlot;

    public Appointment(LocalDate date, int duration, int participants,
                       AppointmentType type, Client client) {
        this.date = date;
        this.duration = duration;
        this.participants = participants;
        this.type = type;
        this.client = client;
        this.status = "Pending";
    }

    public Client getOwner() {
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

    public LocalDate getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public int getParticipants() {
        return participants;
    }

    public AppointmentType getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}