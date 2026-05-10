package soft;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Service responsible for managing appointment operations such as booking,
 * cancellation, and modification.
 * <p>
 * It applies multiple booking rules to ensure that all appointments
 * meet system constraints before being confirmed.
 * </p>
 * 
 * @author Tala Jaber
 */
public class BookingService {

    private static final Logger logger = Logger.getLogger(BookingService.class.getName());

    private List<BookingRuleStrategy> rules;
    private int maxDuration;
    private int maxParticipants;

    /**
     * Creates a new BookingService with system constraints and initializes
     * all booking validation rules.
     *
     * @param maxParticipants maximum allowed participants per appointment
     * @param maxDuration maximum allowed duration for an appointment
     */
    public BookingService(int maxParticipants, int maxDuration) {

        rules = new ArrayList<>();
        this.maxDuration = maxDuration;
        this.maxParticipants = maxParticipants;

        // Sprint 2 rules
        rules.add(new DurationRule(this.maxDuration));
        rules.add(new ParticipantLimitRule(this.maxParticipants));

        // Sprint 5 rules
        rules.add(new UrgentRule());
        rules.add(new GroupRule());
        rules.add(new IndividualRule());
        rules.add(new VirtualRule());
        rules.add(new InPersonRule());
        rules.add(new FollowUpRule());
        rules.add(new AssessmentRule());
    }

    /**
     * Attempts to book an appointment into a given time slot.
     * The method validates date, rules, and available time before confirmation.
     *
     * @param appointment the appointment to be booked
     * @param timeslot the time slot where the appointment will be placed
     * @return true if booking is successful, false otherwise
     */
    public boolean bookAppointment(Appointment appointment, TimeSlot timeslot) {

        if (appointment.getDate().isBefore(LocalDate.now())) {
            logger.warning("Booking rejected: Date error");
            return false;
        }

        for (BookingRuleStrategy rule : rules) {
            if (!rule.isValid(appointment)) {
                logger.warning("Booking rejected: rule violated");
                return false;
            }
        }

        if (appointment.getDuration() > timeslot.getRemainingTime()) {
            logger.warning("Booking rejected: not enough time");
            return false;
        }

        appointment.setStatus("Confirmed");
        timeslot.addAppointment(appointment);
        appointment.setTimeSlot(timeslot);

        logger.info("Appointment booked successfully!");
        return true;
    }

    /**
     * Cancels an existing appointment if the requester is authorized.
     *
     * @param appointment the appointment to cancel
     * @param requester the user requesting the cancellation (Client or Company/Admin)
     * @return true if cancellation succeeds, false otherwise
     */
    public boolean cancelAppointment(Appointment appointment, Object requester) {

        if (appointment == null || requester == null) {
            return false;
        }

        if (!appointment.isFuture()) {
            logger.warning("Cannot cancel a past appointment.");
            return false;
        }

        boolean isAdmin = requester instanceof Company;

        if (requester instanceof Client) {
            Client client = (Client) requester;

            if (!appointment.getOwner().equals(client) && !isAdmin) {
                logger.warning("You cannot cancel an appointment that is not yours.");
                return false;
            }
        }

        appointment.getTimeSlot().removeAppointment(appointment);
        appointment.setTimeSlot(null);
        appointment.setStatus("Cancelled");

        logger.info("Appointment cancelled successfully.");
        return true;
    }

    /**
     * Modifies an existing appointment by moving it to a new time slot
     * if constraints and permissions are satisfied.
     *
     * @param appointment the appointment to modify
     * @param newSlot the new time slot
     * @param requester the user requesting modification
     * @return true if modification is successful, false otherwise
     */
    public boolean modifyAppointment(Appointment appointment, TimeSlot newSlot, Object requester) {

        TimeSlot oldSlot = appointment.getTimeSlot();

        if (appointment == null || newSlot == null || requester == null) {
            return false;
        }

        if (!appointment.isFuture()) {
            logger.warning("Past appointments cannot be modified.");
            return false;
        }

        boolean isAdmin = requester instanceof Company;

        if (requester instanceof Client) {
            Client client = (Client) requester;

            if (!appointment.getOwner().equals(client) && !isAdmin) {
                logger.warning("You cannot modify an appointment that is not yours.");
                return false;
            }
        }

        oldSlot.removeAppointment(appointment);

        if (appointment.getDuration() > newSlot.getRemainingTime()) {
            oldSlot.addAppointment(appointment);
            logger.warning("Not enough remaining time in new slot.");
            return false;
        }

        newSlot.addAppointment(appointment);
        appointment.setTimeSlot(newSlot);

        logger.info("Appointment modified successfully.");
        return true;
    }
}