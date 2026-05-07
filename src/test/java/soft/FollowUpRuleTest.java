package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FollowUpRuleTest {

    @Test
    void followUpRule_should_pass_when_valid() {
        FollowUpRule rule = new FollowUpRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                20,   // ✔ الحد الأعلى
                1,    // ✔ مشارك واحد
                AppointmentType.FOLLOW_UP,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void followUpRule_should_fail_when_duration_more_than_20() {
        FollowUpRule rule = new FollowUpRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,   // ❌ أكثر من 20
                1,
                AppointmentType.FOLLOW_UP,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void followUpRule_should_fail_when_participants_not_equal_1() {
        FollowUpRule rule = new FollowUpRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                15,
                2,   // ❌ لازم 1
                AppointmentType.FOLLOW_UP,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void followUpRule_should_pass_when_not_followUp_type() {
        FollowUpRule rule = new FollowUpRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                100,
                10,
                AppointmentType.GROUP, // مش FOLLOW_UP
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}
