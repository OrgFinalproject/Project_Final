package soft;

/**
 * Strategy interface for booking validation rules.
 * Each implementation defines a specific business rule
 * that determines whether an appointment is valid or not.
 *
 * This follows the Strategy Design Pattern to allow
 * flexible and extensible validation logic.
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public interface BookingRuleStrategy {

    /**
     * Validates whether the given appointment satisfies a specific rule.
     *
     * @param app the appointment to be validated
     * @return true if the appointment is valid according to the rule,
     *         false otherwise
     */
    boolean isValid(Appointment app);
}