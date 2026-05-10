package soft;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company or service provider (e.g., car rental, real estate office).
 * Each company has its own schedules and is registered in the central system (Admin)
 * to allow clients to browse and book appointments.
 *
 * @author Nadeen and Tala Jaber
 * @version 1.2
 */
public class Company {

    /** The company name */
    private String companyName;

    /** Type of service provided (e.g., Car Rental, Real Estate) */
    private String serviceType;

    /** List of schedules belonging to this company */
    private List<Schedule> schedules;

    /**
     * Creates a new company instance and initializes its schedule list.
     *
     * @param companyName the name of the company
     * @param serviceType the type of service provided
     */
    public Company(String companyName, String serviceType) {
        this.companyName = companyName;
        this.serviceType = serviceType;
        this.schedules = new ArrayList<>();
    }

    /**
     * Adds a new schedule to the company.
     *
     * @param schedule the schedule to be added
     */
    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    /**
     * Returns the company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Returns all schedules related to the company.
     *
     * @return list of schedules
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Displays all available time slots for this company.
     * Implements requirement US1.3 - View available appointment slots.
     *
     * @return true if available slots exist, false otherwise
     */
    public boolean displayAvailableSlots() {
        System.out.println("--- Available slots for company: " + companyName + " ---");

        boolean found = false;

        for (Schedule schedule : schedules) {
            for (TimeSlot slot : schedule.getAllTimeSlots()) {

                if (slot.isAvailable()) {
                    System.out.println(
                            "Date: " + schedule.getDate() +
                            " | Time: " + slot.getStartTime() +
                            " - " + slot.getEndTime()
                    );
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No available slots at the moment for this company.");
        }

        return found;
    }
}