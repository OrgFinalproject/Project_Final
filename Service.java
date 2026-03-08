package Software;

public class Service{

    private String name;
    private String Type;

    public Service(String name, String Type) {
        this.name = name;
        this.Type=Type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return Type;
    }
}