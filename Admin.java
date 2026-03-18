package Software;

/**
 * يمثل هذا الكلاس النظام الإداري (Administrator) الذي يعمل كخادم مركزي (Central Server).
 * وظيفته الأساسية هي إدارة بيانات الدخول وتخزين كافة الشركات (Providers) المسجلة في النظام.
 * * @author Nadeen
 * @version 1.1
 */
public class Admin {

    /** اسم المستخدم الخاص بالأدمن للدخول إلى السيرفر */
    private String username;

    /** كلمة المرور الخاصة بالأدمن */
    private String password;

    /** * قائمة الشركات المسجلة داخل السيرفر.
     * تعمل هذه القائمة كمستودع بيانات (Persistence Layer) في الذاكرة حالياً.
     */
    private List<Company> registeredCompanies = new ArrayList<>();

    /**
     * منشئ الكلاس لتعريف بيانات الأدمن الأساسية.
     * * @param username اسم المستخدم المطلوب للدخول.
     * @param password كلمة المرور المطلوبة للدخول.
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * تسجيل شركة جديدة (مثل شركة سيارات أو عقارات) في السيرفر المركزي.
     * تحقق هذه الميثود فكرة تخزين الشركات التي طلبتها الدكتورة.
     * * @param company كائن الشركة المراد إضافته إلى النظام.
     */
    public void registerNewCompany(Company company) {
        registeredCompanies.add(company);
    }

    /**
     * الحصول على كافة الشركات المسجلة في السيرفر.
     * تستخدم هذه الميثود لعرض الشركات للزبائن (Clients) ليختاروا منها.
     * * @return قائمة تحتوي على كائنات الشركات المسجلة.
     */
    public List<Company> getRegisteredCompanies() {
        return registeredCompanies;
    }

    /**
     * الحصول على اسم المستخدم (يستخدم في AdminAuthService).
     * * @return اسم المستخدم الحالي.
     */
    public String getUsername() { 
        return username; 
    }

    /**
     * الحصول على كلمة المرور (يستخدم للتحقق من الهوية).
     * * @return كلمة المرور الحالية.
     */
    public String getPassword() { 
        return password; 
    }
}
