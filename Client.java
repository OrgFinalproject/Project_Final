package Software;
public class Client {//هاد كلاس الزبون الي بده يحجز 
    private String name;
    private String email;
    private String phoneNumber;

    public Client(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { 
    	return name;
    	}
    public String getEmail() { 
    	return email; 
    	} 
}