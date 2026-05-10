package soft;

import java.util.ArrayList;
import java.util.List;

/**
 * يمثل هذا الكلاس النظام الإداري (Administrator) الذي يعمل كخادم مركزي.
 * وظيفته الأساسية هي إدارة بيانات الدخول وتخزين كافة الشركات المسجلة في النظام.
 *
 * @author Nadeen
 * @version 1.1
 */
public class Admin {

    /** اسم المستخدم الخاص بالأدمن للدخول إلى السيرفر */
    private String username;

    /** كلمة المرور الخاصة بالأدمن */
    private String password;

    /**
     * قائمة الشركات المسجلة داخل السيرفر.
     * تعمل هذه القائمة كمستودع بيانات في الذاكرة حالياً.
     */
    private List<Company> registeredCompanies = new ArrayList<>();

    /**
     * منشئ الكلاس لتعريف بيانات الأدمن الأساسية.
     *
     * @param username اسم المستخدم المطلوب للدخول
     * @param password كلمة المرور المطلوبة للدخول
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * تسجيل شركة جديدة في النظام.
     *
     * @param company كائن الشركة المراد إضافته
     */
    public void registerNewCompany(Company company) {
        registeredCompanies.add(company);
    }

    /**
     * الحصول على جميع الشركات المسجلة.
     *
     * @return قائمة الشركات
     */
    public List<Company> getRegisteredCompanies() {
        return registeredCompanies;
    }

    /**
     * @return اسم المستخدم
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return كلمة المرور
     */
    public String getPassword() {
        return password;
    }
}