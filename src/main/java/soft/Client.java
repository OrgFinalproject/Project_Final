package soft;

/**
 * Represents a client who uses the system to browse and book appointments.
 * This class belongs to the Domain Layer and stores the basic information
 * required for communication with the client.
 *
 * @author Nadeen 
 * @version 1.0
 */
public class Client {

    /** Full name of the client */
    private String name;

    /** Email address used for sending notifications */
    private String email;

    /** Phone number for contact purposes */
    private String phoneNumber;

    /**
     * Constructs a new Client instance.
     *
     * @param name the full name of the client
     * @param email the email address of the client
     * @param phoneNumber the phone number of the client
     */
    public Client(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the client's name.
     *
     * @return the client name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the client's email address.
     *
     * @return the client email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the client's phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}