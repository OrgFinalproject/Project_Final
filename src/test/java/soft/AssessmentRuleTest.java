package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class AssessmentRuleTest {

    @Test
    void assessmentRule_should_pass_when_valid() {
        AssessmentRule rule = new AssessmentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                45,   // ✔ بين 30 و 60
                1,    // ✔ مشارك واحد
                AppointmentType.ASSESSMENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }

    @Test
    void assessmentRule_should_fail_when_duration_less_than_30() {
        AssessmentRule rule = new AssessmentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                20,   // ❌ أقل من 30
                1,
                AppointmentType.ASSESSMENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void assessmentRule_should_fail_when_duration_more_than_60() {
        AssessmentRule rule = new AssessmentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                90,   // ❌ أكثر من 60
                1,
                AppointmentType.ASSESSMENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void assessmentRule_should_fail_when_participants_not_equal_1() {
        AssessmentRule rule = new AssessmentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                45,
                2,   // ❌ لازم 1
                AppointmentType.ASSESSMENT,
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertFalse(rule.isValid(app));
    }

    @Test
    void assessmentRule_should_pass_when_not_assessment_type() {
        AssessmentRule rule = new AssessmentRule();

        Appointment app = new Appointment(
                LocalDate.now().plusDays(1),
                200,
                10,
                AppointmentType.VIRTUAL, // مش assessment
                new Client("Ali", "ali@gmail.com", "0599")
        );

        assertTrue(rule.isValid(app));
    }
}
