package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ScheduleTest {

    private Schedule schedule;
    private TimeSlot slot1;
    private TimeSlot slot2;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting Schedule Tests ===");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("=== All Schedule Tests Finished ===");
    }

    @BeforeEach
    void setUp() throws Exception {
        schedule = new Schedule(1, LocalDate.now());

        slot1 = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0));
        slot2 = new TimeSlot(LocalTime.of(10, 0), LocalTime.of(11, 0));
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished\n");
    }

    // 🔹 Constructor
    @Test
    void testConstructor() {
        assertEquals(1, schedule.getId());
        assertEquals(LocalDate.now(), schedule.getDate());
        assertTrue(schedule.getAllTimeSlots().isEmpty());
    }

    // 🔹 addTimeSlot
    @Test
    void testAddTimeSlot() {
        schedule.addTimeSlot(slot1);

        assertTrue(schedule.hasTimeSlot(slot1));
        assertEquals(1, schedule.getAllTimeSlots().size());
    }

    // 🔹 add multiple slots
    @Test
    void testAddMultipleTimeSlots() {
        schedule.addTimeSlot(slot1);
        schedule.addTimeSlot(slot2);

        assertEquals(2, schedule.getAllTimeSlots().size());
        assertTrue(schedule.hasTimeSlot(slot1));
        assertTrue(schedule.hasTimeSlot(slot2));
    }

    // 🔹 removeTimeSlot success
    @Test
    void testRemoveTimeSlotSuccess() {
        schedule.addTimeSlot(slot1);

        boolean result = schedule.removeTimeSlot(slot1);

        assertTrue(result);
        assertFalse(schedule.hasTimeSlot(slot1));
    }

    // 🔹 removeTimeSlot fail
    @Test
    void testRemoveTimeSlotFail() {
        boolean result = schedule.removeTimeSlot(slot1);

        assertFalse(result);
    }

    // 🔹 hasTimeSlot
    @Test
    void testHasTimeSlot() {
        schedule.addTimeSlot(slot1);

        assertTrue(schedule.hasTimeSlot(slot1));
        assertFalse(schedule.hasTimeSlot(slot2));
    }

    // 🔹 getAllTimeSlots
    @Test
    void testGetAllTimeSlots() {
        schedule.addTimeSlot(slot1);

        assertEquals(1, schedule.getAllTimeSlots().size());
        assertEquals(slot1, schedule.getAllTimeSlots().get(0));
    }
}
