package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class InPersonRuleTest {

    @Test
    void inPersonRule_should_pass_when_valid() {
        InPersonRule rule = new InPersonRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                60,
                5,   // الحد المسموح
                AppointmentType.IN_PERSON,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void inPersonRule_should_fail_when_participants_more_than_5() {
        InPersonRule rule = new InPersonRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                60,
                6,   // ❌ invalid
                AppointmentType.IN_PERSON,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void inPersonRule_should_pass_when_not_in_person_type() {
        InPersonRule rule = new InPersonRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                60,
                20,
                AppointmentType.VIRTUAL, // not IN_PERSON
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}
