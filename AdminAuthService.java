package Software;


/**
 * توفر هذه الفئة خدمات التحقق من الهوية (Authentication) للأدمن.
 * تعمل كطبقة خدمة (Service Layer) تفصل بين بيانات الأدمن ومنطق تسجيل الدخول.
 * * @author Nadeen & Tala Jaber
 * @version 1.0
 */
public class AdminAuthService {

    /** مرجع لكائن الأدمن (السيرفر) للتحقق من بياناته */
    private Admin admin;
    
    /** حالة تسجيل الدخول الحالية */
    private boolean loggedIn = false;

    /**
     * منشئ الكلاس لربط خدمة تسجيل الدخول بالسيرفر المركزي.
     * * @param admin كائن الأدمن الذي يحتوي على اليوزر والباسورد المخزنين.
     */
    public AdminAuthService(Admin admain) {
        this.admin = admin;
    }

    /**
     * تنفيذ عملية تسجيل الدخول.
     * يحقق متطلب US1.1: Administrator login.
     * * @param username اسم المستخدم المدخل.
     * @param password كلمة المرور المدخلة.
     * @return true إذا كانت البيانات صحيحة، false خلاف ذلك.
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
     * تنفيذ عملية تسجيل الخروج.
     * يحقق متطلب US1.2: Administrator logout.
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
     * التحقق مما إذا كان الأدمن مسجل دخوله حالياً.
     * * @return حالة تسجيل الدخول.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
