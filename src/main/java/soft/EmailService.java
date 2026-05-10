package soft;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

/**
 * EmailService is responsible for sending email notifications to clients.
 * It implements the Observer interface and acts as a notification subscriber
 * in the appointment booking system.
 *
 * This service uses Jakarta Mail (SMTP) to send emails through Gmail server.
 *
 * @author Nadeen
 * @version 1.0
 */
public class EmailService implements Observer {

    /** Email username used for SMTP authentication */
    private final String username;

    /** Email password or app password used for SMTP authentication */
    private final String password;

    /**
     * Constructs an EmailService with SMTP credentials.
     *
     * @param username sender email address
     * @param password sender email password or app password
     */
    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sends an email to a specific recipient using SMTP configuration.
     *
     * @param to recipient email address
     * @param subject email subject
     * @param body email message content
     */
    private void sendEmail(String to, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent to " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a notification email when an appointment event occurs.
     * This method is called by the notification system (Observer pattern).
     *
     * @param user client receiving the notification
     * @param message email message content
     */
    @Override
    public void update(Client user, String message) {
        sendEmail(user.getEmail(), "Appointment Reminder", message);
    }
}