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

class TimeSlotTest {

    private TimeSlot timeSlot;
    private Appointment appointment30;
    private Appointment appointment60;
    private Appointment appointment70;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting TimeSlot Tests ===");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("=== All TimeSlot Tests Finished ===");
    }

    @BeforeEach
    void setUp() throws Exception {
        timeSlot = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0));

        appointment30 = new Appointment(LocalDate.now().plusDays(1), 30, 2, null, null);
        appointment60 = new Appointment(LocalDate.now().plusDays(1), 60, 1, null, null);
        appointment70 = new Appointment(LocalDate.now().plusDays(1), 70, 3, null, null);
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished\n");
    }

    @Test
    void testConstructorValid() {
        assertEquals(LocalTime.of(9, 0), timeSlot.getStartTime());
        assertEquals(LocalTime.of(10, 0), timeSlot.getEndTime());
        assertTrue(timeSlot.isAvailable());
        assertEquals(60, timeSlot.getRemainingTime());
    }

    @Test
    void testConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TimeSlot(LocalTime.of(10, 0), LocalTime.of(9, 0));
        });
    }

    @Test
    void testAddAppointmentSuccess() {
        assertTrue(timeSlot.addAppointment(appointment30));
        assertEquals(30, timeSlot.getRemainingTime());
        assertTrue(timeSlot.isAvailable());
    }

    @Test
    void testAddAppointmentNotEnoughTime() {
        assertFalse(timeSlot.addAppointment(appointment70));
        assertEquals(60, timeSlot.getRemainingTime());
        assertTrue(timeSlot.isAvailable());
    }

    @Test
    void testAddAppointmentWhenBooked() {
        timeSlot.book();
        assertFalse(timeSlot.addAppointment(appointment30));
        assertEquals(60, timeSlot.getRemainingTime());
    }

    @Test
    void testAddAppointmentFillSlotCompletely() {
        assertTrue(timeSlot.addAppointment(appointment60));
        assertEquals(0, timeSlot.getRemainingTime());
        assertFalse(timeSlot.isAvailable());
    }

    @Test
    void testRemoveAppointment() {
        timeSlot.addAppointment(appointment60);
        assertEquals(0, timeSlot.getRemainingTime());
        assertFalse(timeSlot.isAvailable());

        assertTrue(timeSlot.removeAppointment(appointment60));
        assertEquals(60, timeSlot.getRemainingTime());
        assertTrue(timeSlot.isAvailable());
    }

    @Test
    void testBook() {
        timeSlot.book();
        assertFalse(timeSlot.isAvailable());
    }

    @Test
    void testBookTwice() {
        timeSlot.book();
        assertThrows(IllegalArgumentException.class, () -> {
            timeSlot.book();
        });
    }

    @Test
    void testUnbook() {
        timeSlot.book();
        timeSlot.unbook();
        assertTrue(timeSlot.isAvailable());
    }

    @Test
    void testUnbookWithoutBook() {
        assertThrows(IllegalArgumentException.class, () -> {
            timeSlot.unbook();
        });
    }
}
