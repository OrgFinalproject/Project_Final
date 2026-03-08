package Software;

public class AdminAuthService {

    private Admin admin;
    private boolean loggedIn = false;

    public AdminAuthService(Admin admin) {
        this.admin = admin;
    }

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

    public void logout() {

        if (loggedIn) {
            loggedIn = false;
            System.out.println("Logged out successfully");
        } else {
            System.out.println("No admin is logged in");
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}