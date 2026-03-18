package Software;

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
}
