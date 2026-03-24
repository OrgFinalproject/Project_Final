package software;
/*auther Tala Barhoush&tala*/
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import soft.Appointment;

package software;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import soft.Appointment;

public class TimeSlot {

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;
    private List<Appointment> appointments;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true;
        this.appointments = new ArrayList<>();
    }

    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public boolean isAvailable() { return isAvailable; }

    public boolean addAppointment(Appointment appointment) {
        if (!isAvailable) return false;
        if (appointment.getDuration() > getRemainingTime()) return false;
        appointments.add(appointment);
        return true;
    }

    public boolean removeAppointment(Appointment appointment)
    {
 	   
       appointments.remove(appointment);
        return true;
    }
    
    public long getRemainingTime() {
        long totalBookedMinutes = appointments.stream()
                .mapToLong(Appointment::getDuration)
                .sum();
        return java.time.Duration.between(startTime, endTime).toMinutes() - totalBookedMinutes;
    }

    public void book() {
        if (isAvailable) isAvailable = false;
        else throw new IllegalArgumentException("Already booked");
    }

    public void unbook() {
        if (!isAvailable) isAvailable = true;
        else throw new IllegalArgumentException("Already unbooked");
    }
}
