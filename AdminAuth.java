package Software;


public class AdminAuthService {

    private Admin admin;
    private boolean loggedIn = false;

    public  AdminAuthService (Admin admin) {
        this.admin = admin;
    }

    public boolean login(String username, String password) {

        if (admin.getUsername().equals(username) &&
            admin.getPassword().equals(password)) {

            loggedIn = true;
            return true;
        }

        return false;
    }

    public void logout() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}