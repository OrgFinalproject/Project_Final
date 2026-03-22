package software;
/*Edited by Tala Barhoush*/
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import soft.Appointment;

public class TimeSlot {

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;

  
    private List<Appointment> appointments;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {

        //  تعديل: تحقق من صحة الوقت
        // السبب: منع إنشاء TimeSlot غير منطقي (end قبل start)
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after start time!");
        }

        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true;

        //  تعديل مهم جداً: تهيئة الـ List
        // السبب: بدونها بصير NullPointerException عند add
        this.appointments = new ArrayList<>();
    }

    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public boolean isAvailable() { return isAvailable; }

    //  تعديل: إضافة تحقق قبل إضافة الموعد
    public boolean addAppointment(Appointment appointment) {

        //  new: تحقق إذا الـ TimeSlot متاح
        // السبب: منع الحجز إذا كان slot محجوز بالكامل
        if (!isAvailable) {
            System.out.println("TimeSlot is not available");
            return false;
        }

        //   newتحقق من الوقت المتبقي
        // السبب: منع تجاوز الوقت (overbooking)
        if (appointment.getDuration() > getRemainingTime()) {
            System.out.println("Not enough time in this slot");
            return false;
        }

        //  إضافة الموعد بعد التحقق
        appointments.add(appointment);
        return true;
    }

    //  تعديل: حساب الوقت المتبقي بناءً على المواعيد المحجوزة
    public long getRemainingTime() {

        // new جمع مدة كل المواعيد
        // السبب: معرفة الوقت المستهلك داخل الـ slot
        long totalBookedMinutes = appointments.stream()
                .mapToLong(Appointment::getDuration)
                .sum();

        //  حساب الوقت المتبقي
        // السبب: total time - booked time
        return java.time.Duration.between(startTime, endTime).toMinutes()
                - totalBookedMinutes;
    }

    public void book() {
        //  نفس المنطق new
        if (isAvailable) {
            isAvailable = false;
        } else {
            throw new IllegalArgumentException("Already booked");
        }
    }

    public void unbook() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
            throw new IllegalArgumentException("Already unbooked");
        }
    }
}
