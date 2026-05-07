package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class GroupRuleTest {

    @Test
    void groupRule_should_pass_when_valid() {
        GroupRule rule = new GroupRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                90,   // duration OK
                5,    // participants OK
                AppointmentType.GROUP,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void groupRule_should_fail_when_participants_less_than_2() {
        GroupRule rule = new GroupRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                90,
                1,    // ❌ invalid
                AppointmentType.GROUP,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void groupRule_should_fail_when_duration_less_than_60() {
        GroupRule rule = new GroupRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,   // ❌ invalid
                5,
                AppointmentType.GROUP,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void groupRule_should_pass_when_not_group_type() {
        GroupRule rule = new GroupRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                10,
                1,
                AppointmentType.VIRTUAL, // not GROUP
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}