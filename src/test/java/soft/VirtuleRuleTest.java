package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class VirtualRuleTest {

    @Test
    void virtualRule_should_pass_when_valid() {
        VirtualRule rule = new VirtualRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,
                5,
                AppointmentType.VIRTUAL,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void virtualRule_should_fail_when_participants_more_than_limit() {
        VirtualRule rule = new VirtualRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,
                9,   // ❌ invalid
                AppointmentType.VIRTUAL,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void virtualRule_should_pass_when_not_virtual_type() {
        VirtualRule rule = new VirtualRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                100,
                20,
                AppointmentType.GROUP, // not virtual
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}