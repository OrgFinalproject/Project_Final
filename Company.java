package Soft;

import java.util.ArrayList;
import java.util.List;
import software.Schedule;

/**
 * تمثل هذه الفئة الشركة أو مزود الخدمة (مثل شركة سيارات أو مكتب عقارات).
 * يتم تخزين كائنات هذه الفئة في السيرفر المركزي (Admin) لتمكين الزبائن من الحجز.
 * * @author Nadeen
 * @version 1.1
 */
public class Company {

    /** اسم الشركة التجاري */
    private String companyName;

    /** نوع الخدمة المقدمة (مثلاً: Car Rental) */
    private String serviceType;

    /** قائمة الجداول الزمنية الخاصة بهذه الشركة فقط */
    private List<Schedule> schedules;

    /**
     * منشئ الكلاس لتعريف شركة جديدة وتجهيز قائمة جداولها.
     * * @param companyName اسم الشركة.
     * @param serviceType تخصص الشركة أو نوع خدمتها.
     */
    public Company(String companyName, String serviceType) {
        this.companyName = companyName;
        this.serviceType = serviceType;
        this.schedules = new ArrayList<>();
    }

    /**
     * إضافة جدول زمني جديد (ليوم معين مثلاً) لهذه الشركة.
     * * @param schedule كائن الجدول المراد إضافته.
     */
    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    /**
     * الحصول على اسم الشركة.
     * @return اسم الشركة كـ String.
     */
    public String getCompanyName() { 
        return companyName; 
    }

    /**
     * الحصول على قائمة الجداول المرتبطة بالشركة.
     * @return قائمة تحتوي على جداول المواعيد.
     */
    public List<Schedule> getSchedules() { 
        return schedules; 
    }
    /**
 * عرض كافة الفترات الزمنية المتاحة للشركة.
 * يحقق متطلب US1.3 - View available appointment slots.
 * @version 1.2
 */
public void displayAvailableSlots() {
    System.out.println("--- المواعيد المتاحة لشركة: " + companyName + " ---");
    boolean found = false;
    
    for (Schedule schedule : schedules) {
        for (TimeSlot slot : schedule.getAllTimeSlots()) {
            if (slot.isAvailable()) {
                System.out.println("التاريخ: " + schedule.getDate() + 
                                   " | الوقت: " + slot.getStartTime() + " - " + slot.getEndTime());
                found = true;
            }
        }
    }
    
    if (!found) {
        System.out.println("نعتذر، لا توجد مواعيد متاحة حالياً لهذه الشركة.");
    }
}
}
