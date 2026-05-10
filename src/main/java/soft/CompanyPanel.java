package soft;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CompanyPanel represents the main graphical dashboard for managing companies.
 * It allows users to register new companies, view existing ones, and navigate
 * to their schedules and available time slots.
 *
 * The interface is built using Java Swing and follows a modern UI style
 * with a blue-themed design system.
 *
 * Features:
 * - Register new companies
 * - Display list of companies
 * - Navigate to schedule management
 * - Predefined time slots for each company
 *
 * @author Nadeen
 * @version 1.0
 */
public class CompanyPanel extends JFrame {

    // ================= UI COLORS =================

    /** Primary blue color used for headers and main buttons */
    private static final Color PRIMARY_BLUE = new Color(59, 113, 229);

    /** Darker shade of blue used for hover effects */
    private static final Color DARK_BLUE = new Color(30, 58, 138);

    /** Light background color for the main panel */
    private static final Color BG_LIGHT = new Color(240, 245, 255);

    /** White card color (reserved for future UI components) */
    private static final Color CARD_WHITE = Color.WHITE;

    // ================= DATA =================

    /** List that holds all registered company objects */
    private List<Company> companyObjects = new ArrayList<>();

    /** Model used to display company names in the UI list */
    private DefaultListModel<String> listModel;

    /** UI list component showing company names */
    private JList<String> companyList;

    /**
     * Constructor initializes the Company Management dashboard UI.
     */
    public CompanyPanel() {
        setTitle("Dashboard - Company Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 800);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(BG_LIGHT);

        // ================= HEADER =================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setBorder(new EmptyBorder(40, 20, 40, 20));

        JLabel iconLabel = new JLabel("🏢");
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

        // ================= CONTENT =================
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JButton btnRegister = createStyledButton("➕ Register New Company", true);
        btnRegister.setPreferredSize(new Dimension(0, 55));

        btnRegister.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Company Name:");
            if (name == null || name.trim().isEmpty()) return;

            String service = JOptionPane.showInputDialog(this, "Service Type:");
            if (service == null || service.trim().isEmpty()) return;

            Company newComp = new Company(name, service);

            Schedule schedule = new Schedule(1, LocalDate.now());
            schedule.addTimeSlot(new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0)));
            schedule.addTimeSlot(new TimeSlot(LocalTime.of(10, 0), LocalTime.of(11, 0)));
            schedule.addTimeSlot(new TimeSlot(LocalTime.of(11, 0), LocalTime.of(12, 0)));

            newComp.addSchedule(schedule);

            companyObjects.add(newComp);
            listModel.addElement(name + "  •  " + service);
        });

        contentPanel.add(btnRegister, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        companyList = new JList<>(listModel);
        styleJList(companyList);

        JScrollPane scrollPane = new JScrollPane(companyList);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BG_LIGHT);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JButton btnGoToSchedule = createStyledButton("Go to Schedule ➔", false);
        btnGoToSchedule.setPreferredSize(new Dimension(0, 55));

        btnGoToSchedule.addActionListener(e -> {
            int index = companyList.getSelectedIndex();
            if (index != -1) {
                Company selected = companyObjects.get(index);
                new ScheduleUI(selected).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a company first!",
                        "Selection Required",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        contentPanel.add(btnGoToSchedule, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    /**
     * Styles the JList component used for displaying companies.
     *
     * @param list the JList to style
     */
    private void styleJList(JList<String> list) {
        list.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        list.setSelectionBackground(new Color(230, 240, 255));
        list.setSelectionForeground(PRIMARY_BLUE);
        list.setFixedCellHeight(60);
        list.setBorder(new EmptyBorder(10, 10, 10, 10));
        list.setBackground(Color.WHITE);
    }

    /**
     * Creates a styled button used in the UI.
     *
     * @param text button text
     * @param primary whether it is a primary (blue) button or secondary
     * @return styled JButton
     */
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

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (primary) btn.setBackground(DARK_BLUE);
                else btn.setBackground(new Color(245, 250, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (primary) btn.setBackground(PRIMARY_BLUE);
                else btn.setBackground(Color.WHITE);
            }
        });

        return btn;
    }

    /**
     * Main method used to run the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompanyPanel().setVisible(true));
    }
}