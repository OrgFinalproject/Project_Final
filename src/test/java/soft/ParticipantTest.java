package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParticipantLimitRuleTest {

    private ParticipantLimitRule rule;
    private Appointment appointment;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting ParticipantLimitRule Tests ===");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("=== All ParticipantLimitRule Tests Finished ===");
    }

    @BeforeEach
    void setUp() throws Exception {
        rule = new ParticipantLimitRule(5);
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished\n");
    }

    @Test
    void testIsValidWhenParticipantsLessThanLimit() {
        appointment = new Appointment(LocalDate.now().plusDays(1), 30, 3, AppointmentType.INDIVIDUAL, null);

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testIsValidWhenParticipantsEqualToLimit() {
        appointment = new Appointment(LocalDate.now().plusDays(1), 30, 5, AppointmentType.GROUP, null);

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testIsValidWhenParticipantsGreaterThanLimit() {
        appointment = new Appointment(LocalDate.now().plusDays(1), 30, 7, AppointmentType.GROUP, null);

        assertFalse(rule.isValid(appointment));
    }
}
