package Software;

/**
 * تمثل هذه الفئة موعداً (Appointment) يتم إنشاؤه بواسطة الزبون.
 * تحتوي على تفاصيل الحجز مثل الوقت، المدة، وعدد المشاركين.
 * * @author Nadeen & Tala Barhoush
 * @version 2.0 
 */
public class Appointment {

    private String date;
    private String time;
    private String status;
    private int participants;
    private int duration;
    
    /** الزبون الذي قام بطلب هذا الموعد */
    private Client client; 
    // Added in Sprint 5: to support multiple appointment types تعديل 

private AppointmentType type;
    /**
     * منشئ الكلاس لإنشاء طلب موعد جديد.
     * * @param client كائن الزبون الذي يجري الحجز.
     * @param date تاريخ الموعد.
     * @param time وقت البدء.
     * @param duration مدة الموعد بالدقائق.
     * @param participants عدد الأشخاص المشاركين.
     */
    
    // Updated constructor in Sprint 5 to include appointment type تعديل

public Appointment(Client client, String date, String time, int duration, int participants, AppointmentType type) {
    this.client = client;
    this.date = date;
    this.time = time;
    this.duration = duration;
    this.participants = participants;
    this.type = type;// store appointment type (Sprint 5)
    this.status = "Pending";
}

    /** @return تاريخ الموعد */
    public String getDate() { return date; }

    /** @return وقت بدء الموعد */
    public String getTime() { return time; }

    /** @param status حالة الموعد الجديدة (مثل: Confirmed أو Cancelled) */
    public void setStatus(String status) { this.status = status; }

    /** @return مدة الموعد بالدقائق */
    public int getDuration() { return duration; }

    /** @return عدد المشاركين في الموعد */
    public int getParticipants() { return participants; }

    /** @return حالة الموعد الحالية */
    public String getStatus() { return status; }

    /** @return الزبون المرتبط بهذا الموعد */
    public Client getClient() { return client; }
    
    // Getter for appointment type (Sprint 5 feature)

public AppointmentType getType() {
    return type;
}
    /**
     * عرض معلومات الموعد كاملة للزبون أو الأدمن.
     */
    public void displayInfo() {
        System.out.println("=== تفاصيل الموعد ===");
        System.out.println("الزبون: " + client.getName());
        System.out.println("التاريخ: " + date);
        System.out.println("الوقت: " + time);
        System.out.println("المدة: " + duration + " دقيقة");
        System.out.println("عدد المشاركين: " + participants);
        System.out.println("الحالة: " + status);
        System.out.println("====================");
    }
}
