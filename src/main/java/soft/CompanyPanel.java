package soft;
/* * @author Nadeen
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CompanyPanel extends JFrame {

    // لوحة الألوان المعتمدة (Modern Blue Palette)
    private static final Color PRIMARY_BLUE = new Color(59, 113, 229);
    private static final Color DARK_BLUE = new Color(30, 58, 138);
    private static final Color BG_LIGHT = new Color(240, 245, 255); // أزرق فاتح جداً للخلفية
    private static final Color CARD_WHITE = Color.WHITE;

    private List<Company> companyObjects = new ArrayList<>();
    private DefaultListModel<String> listModel;
    private JList<String> companyList;

    public CompanyPanel() {
        setTitle("Dashboard - Company Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 800);
        setLocationRelativeTo(null);

        // اللوحة الرئيسية
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(BG_LIGHT);

        // --- الجزء العلوي (Header مع الرسمة) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setBorder(new EmptyBorder(40, 20, 40, 20));

        JLabel iconLabel = new JLabel("🏢"); // الرسمة (يمكن استبدالها بـ ImageIcon)
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setForeground(Color.WHITE);

        JLabel headerLabel = new JLabel("Company Manager");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel("Manage your partners and schedules");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subLabel.setForeground(new Color(200, 220, 255));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(iconLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        headerPanel.add(headerLabel);
        headerPanel.add(subLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // --- الجزء الأوسط (القائمة والأزرار) ---
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // زر التسجيل (تصميم بارز)
        JButton btnRegister = createStyledButton("➕ Register New Company", true);
        btnRegister.setPreferredSize(new Dimension(0, 55));
        btnRegister.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Company Name:");
            if (name == null || name.trim().isEmpty()) return;

            String service = JOptionPane.showInputDialog(this, "Service Type:");
            if (service == null || service.trim().isEmpty()) return;

            Company newComp = new Company(name, service);
            
            // إضافة بيانات تلقائية كما في كودك
            Schedule schedule = new Schedule(1, LocalDate.now());
            schedule.addTimeSlot(new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0)));
            schedule.addTimeSlot(new TimeSlot(LocalTime.of(10, 0), LocalTime.of(11, 0)));
            schedule.addTimeSlot(new TimeSlot(LocalTime.of(11, 0), LocalTime.of(12, 0)));
            newComp.addSchedule(schedule);

            companyObjects.add(newComp);
            listModel.addElement(name + "  •  " + service);
        });

        contentPanel.add(btnRegister, BorderLayout.NORTH);

        // قائمة الشركات (تصميم البطاقة)
        listModel = new DefaultListModel<>();
        companyList = new JList<>(listModel);
        styleJList(companyList);

        JScrollPane scrollPane = new JScrollPane(companyList);
        scrollPane.setBorder(null); // إزالة الحدود التقليدية
        scrollPane.getViewport().setBackground(BG_LIGHT);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // زر الانتقال
        JButton btnGoToSchedule = createStyledButton("Go to Schedule ➔", false);
        btnGoToSchedule.setPreferredSize(new Dimension(0, 55));
        btnGoToSchedule.addActionListener(e -> {
            int index = companyList.getSelectedIndex();
            if (index != -1) {
                Company selected = companyObjects.get(index);
                new ScheduleUI(selected).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a company first!", "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        });

        contentPanel.add(btnGoToSchedule, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void styleJList(JList<String> list) {
        // تم استبدال Font.MEDIUM بـ Font.PLAIN لأن جافا لا تعرف MEDIUM
        list.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        list.setSelectionBackground(new Color(230, 240, 255));
        list.setSelectionForeground(PRIMARY_BLUE);
        list.setFixedCellHeight(60); 
        list.setBorder(new javax.swing.border.EmptyBorder(10, 10, 10, 10));
        list.setBackground(Color.WHITE);
    }

    private JButton createStyledButton(String text, boolean primary) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (primary) {
            btn.setBackground(PRIMARY_BLUE);
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createEmptyBorder());
        } else {
            btn.setBackground(Color.WHITE);
            btn.setForeground(PRIMARY_BLUE);
            btn.setBorder(new LineBorder(PRIMARY_BLUE, 2, true));
        }
        
        // تأثير عند تمرير الماوس (اختياري)
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(primary) btn.setBackground(DARK_BLUE);
                else btn.setBackground(new Color(245, 250, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(primary) btn.setBackground(PRIMARY_BLUE);
                else btn.setBackground(Color.WHITE);
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        // لتشغيل الشكل الجديد مباشرة للتجربة
        SwingUtilities.invokeLater(() -> new CompanyPanel().setVisible(true));
    }
}
