package soft;

public class Appointment {

    private String date;
    private String time;
    private String status;
    // TODO: إضافة المزيد من الخصائص إذا لزم الأمر 

    public Appointment(String date, String time) {
        this.date = date;
        this.time = time;
        this.status = "Pending";   
    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time; }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    // TODO: إضافة التحقق من صحة التاريخ في الوقت لاحقاً إذا لزم

    public void displayInfo() {
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Status: " + status);
    }
}
