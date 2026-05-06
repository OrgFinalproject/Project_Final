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

class DurationRuleTest {

    private DurationRule rule;
    private Appointment appointment;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting DurationRule Tests ===");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("=== All DurationRule Tests Finished ===");
    }

    @BeforeEach
    void setUp() throws Exception {
        rule = new DurationRule(60);
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished\n");
    }

    // 🔹 أقل من الحد
    @Test
    void testDurationLessThanLimit() {
        appointment = new Appointment(LocalDate.now().plusDays(1), 30, 2, AppointmentType.INDIVIDUAL, null);

        assertTrue(rule.isValid(appointment));
    }

    // 🔹 يساوي الحد
    @Test
    void testDurationEqualToLimit() {
        appointment = new Appointment(LocalDate.now().plusDays(1), 60, 2, AppointmentType.GROUP, null);

        assertTrue(rule.isValid(appointment));
    }

    // 🔹 أكبر من الحد
    @Test
    void testDurationGreaterThanLimit() {
        appointment = new Appointment(LocalDate.now().plusDays(1), 90, 2, AppointmentType.GROUP, null);

        assertFalse(rule.isValid(appointment));
    }
}
