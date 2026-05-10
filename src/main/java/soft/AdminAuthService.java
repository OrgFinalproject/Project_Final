package soft;

/**
 * توفر هذه الفئة خدمات التحقق من الهوية (Authentication) للأدمن.
 * تعمل كطبقة خدمة تفصل بين بيانات الأدمن ومنطق تسجيل الدخول.
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */
public class AdminAuthService {

    /** مرجع لكائن الأدمن للتحقق من بياناته */
    private Admin admin;

    /** حالة تسجيل الدخول الحالية */
    private boolean loggedIn = false;

    /**
     * منشئ الكلاس لربط خدمة تسجيل الدخول بالأدمن.
     *
     * @param admin كائن الأدمن الذي يحتوي على بيانات الدخول
     */
    public AdminAuthService(Admin admin) {
        this.admin = admin;
    }

    /**
     * تنفيذ عملية تسجيل الدخول.
     *
     * @param username اسم المستخدم
     * @param password كلمة المرور
     * @return true إذا كانت البيانات صحيحة
     */
    public boolean login(String username, String password) {
        if (admin.getUsername().equals(username) &&
            admin.getPassword().equals(password)) {

            loggedIn = true;
            System.out.println("Login successful");
            return true;
        }

        System.out.println("Invalid username or password");
        return false;
    }

    /**
     * تسجيل الخروج.
     */
    public void logout() {
        if (loggedIn) {
            loggedIn = false;
            System.out.println("Logged out successfully");
        } else {
            System.out.println("No admin is logged in");
        }
    }

    /**
     * @return حالة تسجيل الدخول
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}