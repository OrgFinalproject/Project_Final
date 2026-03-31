package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeSlotTest {

    private TimeSlot slot;
    private Appointment shortAppointment;
    private Appointment longAppointment;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println(">>> Starting TimeSlot tests");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println(">>> Finished TimeSlot tests");
    }

    @BeforeEach
    void setUp() throws Exception {
        // Create a TimeSlot from 9:00 to 10:00 (60 minutes)
        slot = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0));

        // Dummy Client for testing
        Client dummyClient = new Client("Test Client", "test@example.com","0599");

        // Appointments with different durations
        shortAppointment = new Appointment(LocalDate.now(), 30, 1, dummyClient);
        longAppointment = new Appointment(LocalDate.now(), 90, 1, dummyClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        slot = null;
        shortAppointment = null;
        longAppointment = null;
    }

    @Test
    void testAddAppointmentWithinRemainingTime() {
        boolean added = slot.addAppointment(shortAppointment);
        assertTrue(added, "Short appointment should be added successfully");
        assertEquals(30, slot.getRemainingTime(), "Remaining time should be 30 minutes");
    }

    @Test
    void testAddAppointmentExceedingRemainingTime() {
        boolean added = slot.addAppointment(longAppointment);
        assertFalse(added, "Long appointment should not be added");
        assertEquals(60, slot.getRemainingTime(), "Remaining time should stay at 60 minutes");
    }

    @Test
    void testRemoveAppointmentShouldFreeTime() {
        slot.addAppointment(shortAppointment);
        assertEquals(30, slot.getRemainingTime());

        boolean removed = slot.removeAppointment(shortAppointment);
        assertTrue(removed, "Appointment should be removed successfully");
        assertEquals(60, slot.getRemainingTime(), "Remaining time should return to 60 minutes");
    }
}