package soft;

public class BookingService {

    public boolean bookAppointment(Appointment appointment) {

        appointment.setStatus("Confirmed");

        System.out.println("Appointment booked successfully!");

        return true;
    }
}
