package soft;

import java.time.LocalDate;
import java.time.LocalTime;

public class main7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Appointment ap=new Appointment(LocalDate.of(2026,7,8),30,7,AppointmentType.GROUP,new Client("sss","ss","ss"));
		TimeSlot t = new TimeSlot(LocalTime.of(1,30),LocalTime.of(2,45));
		Schedule d=new Schedule(1,LocalDate.of(2026,7,8));
		BookingService c=new BookingService(8,40);
		d.addTimeSlot(t);
		c.bookAppointment(ap, t);
	}

}
