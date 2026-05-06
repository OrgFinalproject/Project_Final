package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UrgentRuleTest {

    @Test
    void urgentRule_should_pass_when_valid() {
        UrgentRule rule = new UrgentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                20,   // duration OK
                2,    // participants OK
                AppointmentType.URGENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void urgentRule_should_fail_when_duration_more_than_30() {
        UrgentRule rule = new UrgentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                60,   // ❌ too long
                2,
                AppointmentType.URGENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void urgentRule_should_fail_when_participants_more_than_2() {
        UrgentRule rule = new UrgentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                20,
                5,    // ❌ too many participants
                AppointmentType.URGENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void urgentRule_should_pass_when_not_urgent_type() {
        UrgentRule rule = new UrgentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                200,
                10,
                AppointmentType.VIRTUAL, // not urgent
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}