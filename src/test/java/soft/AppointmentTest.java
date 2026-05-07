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

class AppointmentTest {

    private Appointment appointment;
    private TimeSlot timeSlot;
    private Client client;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting Appointment Tests ===");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("=== All Appointment Tests Finished ===");
    }

    @BeforeEach
    void setUp() throws Exception {
        client = new Client("Ahmad", "ahmad@test.com", "0599999999");

        appointment = new Appointment(
                LocalDate.now().plusDays(1),
                30,
                2,
                AppointmentType.INDIVIDUAL,
                client
        );

        timeSlot = new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0));
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished\n");
    }

    // 🔹 Constructor
    @Test
    void testConstructor() {
        assertEquals(LocalDate.now().plusDays(1), appointment.getDate());
        assertEquals(30, appointment.getDuration());
        assertEquals(2, appointment.getParticipants());
        assertEquals("Pending", appointment.getStatus());
        assertEquals(AppointmentType.INDIVIDUAL, appointment.getType());
        assertEquals(client, appointment.getOwner());
    }

    // 🔹 TimeSlot
    @Test
    void testSetAndGetTimeSlot() {
        appointment.setTimeSlot(timeSlot);
        assertEquals(timeSlot, appointment.getTimeSlot());
    }

    // 🔹 Status
    @Test
    void testSetAndGetStatus() {
        appointment.setStatus("Confirmed");
        assertEquals("Confirmed", appointment.getStatus());
    }

    // 🔹 isFuture true
    @Test
    void testIsFutureTrue() {
        appointment.setTimeSlot(timeSlot);
        assertTrue(appointment.isFuture());
    }

    // 🔹 isFuture false
    @Test
    void testIsFutureFalse() {
        Appointment oldAppointment = new Appointment(
                LocalDate.now().minusDays(1),
                30,
                1,
                AppointmentType.URGENT,
                client
        );
        oldAppointment.setTimeSlot(timeSlot);

        assertFalse(oldAppointment.isFuture());
    }

    // 🔹 Client (Owner)
    @Test
    void testGetOwner() {
        assertEquals(client, appointment.getOwner());
    }

    // 🔹 Type
    @Test
    void testGetType() {
        assertEquals(AppointmentType.INDIVIDUAL, appointment.getType());
    }

    // 🔹 Client data
    @Test
    void testClientData() {
        assertEquals("Ahmad", client.getName());
        assertEquals("ahmad@test.com", client.getEmail());
        assertEquals("0599999999", client.getPhoneNumber());
    }
}
