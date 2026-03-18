package Software;

/**
 * تمثل هذه الفئة الزبون (Client) الذي يستخدم النظام لاستعراض المواعيد وإجراء الحجوزات.
 * تندرج هذه الفئة ضمن طبقة النطاق (Domain Layer) وتخزن البيانات الأساسية للتواصل مع الزبون.
 * * @author Nadeenا
 * @version 1.0
 */
public class Client {

    /** اسم الزبون الكامل */
    private String name;

    /** البريد الإلكتروني للزبون (يستخدم لإرسال الإشعارات في Sprint 3) */
    private String email;

    /** رقم هاتف الزبون للتواصل */
    private String phoneNumber;

    /**
     * منشئ الكلاس (Constructor) لإنشاء كائن زبون جديد.
     * * @param name اسم الزبون.
     * @param email البريد الإلكتروني الخاص بالزبون.
     * @param phoneNumber رقم هاتف الزبون.
     */
    public Client(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * الحصول على اسم الزبون.
     * * @return اسم الزبون الحالي.
     */
    public String getName() { 
        return name;
    }

    /**
     * الحصول على البريد الإلكتروني للزبون.
     * * @return البريد الإلكتروني للزبون.
     */
    public String getEmail() { 
        return email; 
    }

    /**
     * الحصول على رقم هاتف الزبون.
     * * @return رقم الهاتف.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
