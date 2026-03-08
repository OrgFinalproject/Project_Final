package Software;

import java.util.ArrayList;
import java.util.List;

public class Admin {

    private String username;
    private String password;

    private List<Service>providers = new ArrayList<>();

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addProvider(Service obj) {
        providers.add(obj);
    }

    public List<Service> getProviders() {
        return providers;
    }
}