package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompanyTest {
	private Company company;
	private Schedule schedule1,schedule2;
	private TimeSlot slot;
	  @BeforeAll
	    static void setUpBeforeClass() throws Exception {
	        System.out.println("=== Starting Company Tests ===");
	    }

	    @AfterAll
	    static void tearDownAfterClass() throws Exception {
	        System.out.println("=== All Company Tests Finished ==="); 
	    }

	@BeforeEach
	void setUp() throws Exception {
        company = new Company("CarCo", "Car Rental");
        schedule1 =new Schedule(17,LocalDate.of(2025, 2, 12));
        schedule2 =new Schedule(18,LocalDate.of(2025, 2, 14));
	}

	@AfterEach
	void tearDown() throws Exception {
	     System.out.println("Test Finished");
	}

	 @Test
	    void testCompanyCreation() {
	        assertEquals("CarCo", company.getCompanyName()); 
	        assertTrue(company.getSchedules().isEmpty());
	    }
	 @Test
	 void testAddSchedule()
	 {	 
		 
		 company.addSchedule(schedule1);
		 assertTrue(company.getSchedules().contains(schedule1)); 
	 }
	 @Test
	 void testAddSchedules()
	 {	 
		 company.addSchedule(schedule1);
		 company.addSchedule(schedule2);
		 assertEquals(2,company.getSchedules().size());
	 }
	 
	 //No Scedules
	 @Test 
	 void displayAvailableSlotsTest1() {
		 company.addSchedule(schedule1);
		assertFalse(company.displayAvailableSlots());
	 }
	 @Test
	 void displayAvailableSlotsTest2() {
		 slot =new TimeSlot(LocalTime.of(1, 30),LocalTime.of(2, 30));
		 schedule1.addTimeSlot(slot);
		 company.addSchedule(schedule1);
		 assertTrue(company.displayAvailableSlots());
	 }
	 @Test
	 void displayAvailableSlotsTest3() {
		 slot =new TimeSlot(LocalTime.of(1, 30),LocalTime.of(2, 30));
			Appointment appointment=new Appointment(LocalDate.of(2023,12,2),60,4,AppointmentType.GROUP,new Client("Omar","Omar.09@gmail.com","1236666"));
			  boolean add= slot.addAppointment(appointment);
        add= slot.addAppointment(appointment);
		 schedule1.addTimeSlot(slot);
		 company.addSchedule(schedule1);
		 assertFalse(company.displayAvailableSlots());
	 }
	 
}
