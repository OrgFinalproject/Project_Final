package soft;

public class Appointment {

    private String date;
    private String time;
    private String status;
    private int participants;
    private int duration;
    // TODO: إضافة المزيد من الخصائص إذا لزم الأمر 

    public Appointment(String date, String time,int duration,int participants) {
        this.date = date;
        this.time = time;
        this.status = "Pending";  
        this.duration=duration;
        this.participants=participants;
    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time; }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getDuration(){
        return duration;
    }
    public int getParticipants(){
        return participants;
    }

    public String getStatus() {
        return status;
    }
    // TODO: إضافة التحقق من صحة التاريخ في الوقت لاحقاً إذا لزم

    public void displayInfo() {
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Duration "+duration);
        System.out.println("Participants "+participants);
        System.out.println("Status: " + status);
        
    }
}
