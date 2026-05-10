package soft;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a schedule for a specific date.
 * A schedule contains multiple time slots that can hold appointments.
 * It provides basic operations to manage time slots such as adding,
 * removing, and checking availability.
 * 
 * @author Nadeen and Tala Barhoush
 * @version 1.0
 */
public class Schedule {

    private long id;
    private LocalDate date;
    private ArrayList<TimeSlot> timeSlots;

    /**
     * Constructs a Schedule object with a specific id and date.
     * 
     * @param id unique identifier of the schedule
     * @param date the date of the schedule
     */
    Schedule(long id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.timeSlots = new ArrayList<>();
    }

    /**
     * Adds a time slot to the schedule.
     * 
     * @param slot the time slot to be added
     */
    public void addTimeSlot(TimeSlot slot) {
        timeSlots.add(slot);
    }

    /**
     * Updates the date of the schedule.
     * 
     * @param date the new date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the schedule ID.
     * 
     * @return schedule id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Returns the date of the schedule.
     * 
     * @return schedule date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns all time slots in the schedule.
     * 
     * @return list of time slots
     */
    public ArrayList<TimeSlot> getAllTimeSlots() {
        return timeSlots;
    }

    /**
     * Removes a time slot from the schedule.
     * 
     * @param slot the time slot to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeTimeSlot(TimeSlot slot) {
        return timeSlots.remove(slot);
    }

    /**
     * Checks whether a specific time slot exists in the schedule.
     * 
     * @param slot the time slot to check
     * @return true if exists, false otherwise
     */
    public boolean hasTimeSlot(TimeSlot slot) {
        return timeSlots.contains(slot);
    }
}