package soft;
/* * @author Nadeen
 * @version 1.0
 */

import jakarta.mail.*;
import jakarta.mail.internet.*;


import java.util.Properties;

public class EmailService implements Observer {

    private final String username;
    private final String password;

    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private void sendEmail(String to, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
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

    public void update(Client user, String message) {
        sendEmail(user.getEmail(), "Appointment Reminder", message);
    }
}
