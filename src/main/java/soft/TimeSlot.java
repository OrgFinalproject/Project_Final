package soft;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a time slot in the scheduling system.
 * Each time slot has a start and end time and can contain multiple appointments.
 * It also tracks availability based on booked appointments and remaining time.
 * 
 * Author: Tala Barhoush and Tala Jaber
 */
public class TimeSlot {

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;
    private List<Appointment> appointments;
    private Schedule schedule;

    /**
     * Constructs a TimeSlot with a given start and end time.
     * 
     * @param startTime the start time of the slot
     * @param endTime the end time of the slot
     * @throws IllegalArgumentException if end time is before start time
     */
    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true;
        this.appointments = new ArrayList<>();
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Adds an appointment to the time slot if there is enough remaining time.
     * 
     * @param appointment the appointment to add
     * @return true if added successfully, false otherwise
     */
    public boolean addAppointment(Appointment appointment) {
        if (!isAvailable) return false;

        if (appointment.getDuration() > getRemainingTime()) {
            return false;
        }

        appointments.add(appointment);
        return true;
    }

    /**
     * Removes an appointment from the time slot.
     * Updates availability based on remaining time.
     * 
     * @param appointment the appointment to remove
     * @return true after removal
     */
    public boolean removeAppointment(Appointment appointment) {

        appointments.remove(appointment);

        if (this.getRemainingTime() > 0) {
            this.isAvailable = true;
        }

        return true;
    }

    /**
     * Calculates the remaining available time in the slot.
     * 
     * @return remaining minutes
     */
    public long getRemainingTime() {

        long totalBookedMinutes = appointments.stream()
                .mapToLong(Appointment::getDuration)
                .sum();

        long remaining = java.time.Duration
                .between(startTime, endTime)
                .toMinutes() - totalBookedMinutes;

        if (remaining == 0) {
            isAvailable = false;
        }

        return remaining;
    }

    /**
     * Marks the time slot as booked.
     * 
     * @throws IllegalArgumentException if already booked
     */
    public void book() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            throw new IllegalArgumentException("Already booked");
        }
    }

    /**
     * Marks the time slot as available again.
     * 
     * @throws IllegalArgumentException if already available
     */
    public void unbook() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
            throw new IllegalArgumentException("Already unbooked");
        }
    }
}