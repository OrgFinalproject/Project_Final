package soft;

/* * @author Nadeen
 * @version 1.0
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanelOnly extends JFrame {

    // 1. تعريف الحقول هنا لتكون مرئية داخل الـ ActionListener
    private JTextField userField;
    private JPasswordField passField;

    private static final Color PRIMARY_BLUE = new Color(59, 113, 229);
    private static final Color TEXT_DARK = new Color(44, 62, 80);
    private static final Color TEXT_GRAY = new Color(127, 140, 141);
    private static final Color BORDER_COLOR = new Color(200, 214, 229);
    private static final Color BG_COLOR = new Color(248, 251, 255);

    public LoginPanelOnly() {
        setTitle("Login - Appointment System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 750);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // الـ UI (نفس الكود الخاص بك)
        JLabel logoLabel = new JLabel("📅"); 
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
        logoLabel.setForeground(PRIMARY_BLUE);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("<html><center>Appointment<br>Scheduling System</center></html>");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Please login to continue");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(TEXT_GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- تعديل هنا: إنشاء الحقول يدوياً أو تخزين المراجع ---
        JPanel userGroup = createInputGroup("👤 Username", "Enter username", false);
        JPanel passGroup = createInputGroup("🔒 Password", "Enter password", true);

        JButton loginButton = new JButton("🔒 LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(PRIMARY_BLUE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setMaximumSize(new Dimension(400, 50));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ==========================================
        // 2. هاد هو الفنكشن (المنطق) في مكانه الصحيح
        // ==========================================
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    
                    // فتح الكلاس الجديد (تأكد من وجود كلاس بهذا الاسم)
                    new CompanyPanel().setVisible(true); 
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // ==========================================

        // باقي إضافة العناصر
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(userGroup);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(passGroup);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(loginButton);
        
        add(mainPanel);
    }

    private JPanel createInputGroup(String labelText, String placeholder, boolean isPassword) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(400, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(TEXT_DARK);

        if (isPassword) {
            passField = new JPasswordField(); // تخزين في المتغير العام
            passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(10, 15, 10, 15)));
            panel.add(passField, BorderLayout.CENTER);
        } else {
            userField = new JTextField(); // تخزين في المتغير العام
            userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(10, 15, 10, 15)));
            panel.add(userField, BorderLayout.CENTER);
        }

        panel.add(label, BorderLayout.NORTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPanelOnly().setVisible(true));
    }
}
