package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class IndividualRuleTest {

    @Test
    void individualRule_should_pass_when_participant_is_1() {
        IndividualRule rule = new IndividualRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,
                1,   // ✔ valid
                AppointmentType.INDIVIDUAL,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void individualRule_should_fail_when_participants_more_than_1() {
        IndividualRule rule = new IndividualRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,
                2,   // ❌ invalid
                AppointmentType.INDIVIDUAL,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void individualRule_should_pass_when_not_individual_type() {
        IndividualRule rule = new IndividualRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                30,
                10,
                AppointmentType.GROUP, // not INDIVIDUAL
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}