package soft;

import javax.swing.*;


import javax.swing.border.*;
import java.awt.*;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Nadeen
 * @version 1.5 (Full Integration with all Appointment Types & Booking Rules)
 */
public class AppointmentUI extends JFrame {
    
    private JTextField nameField, emailField;
    private JSpinner participantsSpinner;
    private JSlider durationSlider; 
    private JComboBox<AppointmentType> typeComboBox; 
    private TimeSlot slot; 

    private static final Color SUCCESS_GREEN = new Color(76, 175, 80);
    private static final Color PRIMARY_BLUE = new Color(59, 113, 229);

    public AppointmentUI(TimeSlot slot) {
        this.slot = slot;
        initUI();
    }

    private void initUI() {
        setTitle("New Booking - " + slot.getSchedule().getDate());
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(25, 30, 25, 30));
        body.setBackground(Color.WHITE);

        
        body.add(new JLabel("📍 Select Appointment Type:"));
        AppointmentType[] allTypes = { 
            AppointmentType.URGENT, 
            AppointmentType.GROUP, 
            AppointmentType.VIRTUAL,
            AppointmentType.INDIVIDUAL,
            AppointmentType.IN_PERSON,
            AppointmentType.FOLLOW_UP,
            AppointmentType.ASSESSMENT 
        };
        typeComboBox = new JComboBox<>(allTypes); 
        typeComboBox.setMaximumSize(new Dimension(500, 40));
        body.add(typeComboBox);
        body.add(Box.createVerticalStrut(15));

    
        body.add(new JLabel("⏱ Duration (Minutes):"));
        durationSlider = new JSlider(10, 60, 30);
        durationSlider.setMajorTickSpacing(10);
        durationSlider.setPaintTicks(true);
        durationSlider.setPaintLabels(true);
        durationSlider.setBackground(Color.WHITE);
        body.add(durationSlider);
        body.add(Box.createVerticalStrut(15));

      
        nameField = createInput(body, "Client Name:");
        emailField = createInput(body, "Email Address:");

        body.add(new JLabel("👥 Number of Participants:"));
        participantsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        participantsSpinner.setMaximumSize(new Dimension(100, 40));
        participantsSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
        body.add(participantsSpinner);
        body.add(Box.createVerticalStrut(30));

        JButton confirmBtn = new JButton("✓ Confirm Booking");
        confirmBtn.setBackground(SUCCESS_GREEN);
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        confirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        confirmBtn.addActionListener(e -> processBooking());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);
        footer.add(confirmBtn);
        
        add(new JScrollPane(body), BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void processBooking() {
        try {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim(); 
            
            if(name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and email!");
                return;
            }

            AppointmentType selectedType = (AppointmentType) typeComboBox.getSelectedItem();
            int duration = durationSlider.getValue();
            int participants = (int) participantsSpinner.getValue();

            Client client = new Client(name, email, "N/A");
            ///add date correctly
            Appointment appt = new Appointment(slot.getSchedule().getDate(), duration, participants, selectedType, client);

            BookingService bookingService = new BookingService(20, 60);
            
            if (bookingService.bookAppointment(appt, slot)) {
                sendNotification(appt, client);

                JOptionPane.showMessageDialog(this, 
                    "✅ Success!\nAppointment: " + selectedType + 
                    "\nConfirmation sent to: " + email);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ Booking Rejected!\nOne of the business rules was violated.", 
                    "Policy Violation", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void sendNotification(Appointment appt, Client client) {
        NotificationService ns = new NotificationService();
        try {
            Dotenv dotenv = Dotenv.load();
            String myEmail = dotenv.get("EMAIL_USERNAME");
            String myPass = dotenv.get("EMAIL_PASSWORD");

            if (myEmail != null && myPass != null) {
                ns.addObserver(new EmailService(myEmail, myPass));
                
                String content = "Dear " + client.getName() + ",\n\n" +
                                 "Your " + appt.getType() + " appointment is confirmed for " +
                                 appt.getDate() + " at " + slot.getStartTime() + ".\n\n" +
                                 "Duration: " + appt.getDuration() + " minutes.";
                
                ns.notifyUser(client, content);
            }
        } catch (Exception e) {
            System.out.println("Email notification skipped: " + e.getMessage());
        }
    }

    private JTextField createInput(JPanel p, String label) {
        p.add(new JLabel(label));
        JTextField f = new JTextField();
        f.setMaximumSize(new Dimension(500, 40));
        p.add(f);
        p.add(Box.createVerticalStrut(10));
        return f;
    }
}
