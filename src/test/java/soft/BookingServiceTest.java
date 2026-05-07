package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingServiceTest {
    private BookingService book;
    private TimeSlot slot1,slot2;
    private Appointment appointment;
    private Client client;
    
    
	static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting BookingService Tests ===");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
        System.out.println("=== All BookingService Tests Finished ==="); 
	}

	@BeforeEach
	void setUp() throws Exception {
		book =new BookingService(5,30);
	}

	@AfterEach
	void tearDown() throws Exception {
		 System.out.println("Test finished\n");
	}

	@Test
	void testBooking_success() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,7,20),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		boolean valid=book.bookAppointment(appointment, slot1);
		assertTrue(valid);
	}
	@Test
	void testBooking_Fail_Duration() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,4,20),60,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		boolean valid=book.bookAppointment(appointment, slot1);
		assertFalse(valid);
	}
	@Test
	void testBooking_Fail_Participants() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,4,20),30,7,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		boolean valid=book.bookAppointment(appointment, slot1);
		assertFalse(valid);
	}
      @Test
	void testBooking_Fail_RemainingTime() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,4,20),30,4,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,0));
		boolean valid=book.bookAppointment(appointment, slot1);
		appointment =new Appointment(LocalDate.of(2026,4,20),30,4,AppointmentType.VIRTUAL,client);
		valid=book.bookAppointment(appointment, slot1);
		assertFalse(valid);
	}
	@Test
	void testCancel_Fail_NULL() {
      boolean valid=book.cancelAppointment(appointment,client);
		assertFalse(valid);
	}
	
	@Test
	void testCancel_Fail_Future() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.now(),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
      boolean valid=book.cancelAppointment(appointment,client);
		assertFalse(valid);
	}
	@Test
	void testCancel_Fail_OtherClient() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,6,6),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
		Client client1=new Client("Omar","Omar.05@gmail.com","0599454545");
      boolean valid=book.cancelAppointment(appointment,client1);
		assertFalse(valid);
	}
    
	@Test
	void testCancel_Success() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2027,5,4),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
      boolean valid=book.cancelAppointment(appointment,client);
		assertTrue(valid);
	}
	
	@Test
	void testCancel_Success_Company() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		Company company = new Company("Cars4","Car-9");
		appointment =new Appointment(LocalDate.of(2026,7,4),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
      boolean valid=book.cancelAppointment(appointment,company);
		assertTrue(valid);
	}
	@Test
	void testModify_Success_Client()
	{
		client =new Client("leen","leen.05@gmail.com","0599454545");
		Company company = new Company("Cars4","Car-9");
		appointment =new Appointment(LocalDate.of(2026,7,4),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		TimeSlot newSlot=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
		boolean valid=book.modifyAppointment(appointment, newSlot, company);
		assertTrue(valid);
	}
	@Test
	void testModify_Success_Comany()
	{
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,7,4),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		TimeSlot newSlot=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
		boolean valid=book.modifyAppointment(appointment, newSlot, client);
		assertTrue(valid);
	}
	
	@Test
	void testModify_Fail_Null()
	{
		//client = NULL
		appointment =new Appointment(LocalDate.of(2026,5,4),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		TimeSlot newSlot=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
		boolean valid=book.modifyAppointment(appointment, newSlot, client);	
		assertFalse(valid);
	}
	@Test
	void testModify_Fail_Future()
	{
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.now(),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		TimeSlot newSlot=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
		boolean valid=book.modifyAppointment(appointment, newSlot, client);	
		assertFalse(valid);
	}
	
	@Test
	void testModify_Fail_OtherClient() {
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,6,6),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		book.bookAppointment(appointment, slot1);
		Client client1=new Client("Omar","Omar.05@gmail.com","0599454545");
      boolean valid=book.modifyAppointment(appointment, slot1, client1);
		assertFalse(valid);
	}
	
	
	@Test
	void testModify_Fail_SlotReserved()
	{
		client =new Client("leen","leen.05@gmail.com","0599454545");
		appointment =new Appointment(LocalDate.of(2026,5,4),30,3,AppointmentType.VIRTUAL,client);
		Appointment appointment2 =new Appointment(LocalDate.of(2026,5,4),30,3,AppointmentType.VIRTUAL,client);
		slot1=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,30));
		TimeSlot newSlot=new TimeSlot(LocalTime.of(4,30),LocalTime.of(5,0));
		book.bookAppointment(appointment, slot1);
		//already booked
		book.bookAppointment(appointment2,newSlot);
		boolean valid=book.modifyAppointment(appointment, newSlot, client);	
		assertFalse(valid);
	}
	
	
}
