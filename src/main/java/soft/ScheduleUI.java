package soft;

/**
 * Schedule UI class responsible for managing company schedules,
 * including adding dates, adding time slots, and booking appointments.
 *
 * Provides a graphical interface for viewing and interacting with
 * schedule data using Java Swing.
 *
 * @author Nadeen and Tala Jaber
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleUI extends JFrame {

    private Company company;
    private JTable table;
    private DefaultTableModel tableModel;

    private static final Color HEADER_BLUE = new Color(58, 118, 208);
    private static final Color BG_LIGHT = new Color(245, 248, 252);

    /**
     * Constructs the Schedule UI for a given company.
     *
     * @param company the company whose schedule will be managed
     */
    public ScheduleUI(Company company) {
        this.company = company;

        if (this.company.getSchedules() == null) {
            System.out.println("Initializing schedules list...");
        }

        setTitle("Schedule Management - " + company.getCompanyName());
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_LIGHT);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BLUE);
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("📅  " + company.getCompanyName() + " - Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        header.add(titleLabel, BorderLayout.WEST);
        mainPanel.add(header, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"Start Time", "End Time", "Status", "Date"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(tableModel);
        styleTable(table);
        refreshTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 25, 10, 25));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer buttons
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
        footer.setOpaque(false);

        JButton btnAddDate = createStyledButton("Add Date", Color.WHITE, Color.BLACK);
        btnAddDate.addActionListener(e -> addNewDate());

        JButton btnAddSlot = createStyledButton("Add Slot", new Color(70, 80, 90), Color.WHITE);
        btnAddSlot.addActionListener(e -> addNewSlot());

        JButton btnBook = createStyledButton("Book Now", new Color(34, 197, 94), Color.WHITE);
        btnBook.addActionListener(e -> openBookingUI());

        footer.add(btnAddDate);
        footer.add(btnAddSlot);
        footer.add(btnBook);

        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Adds a new schedule date to the company schedules list.
     */
    private void addNewDate() {
        String input = JOptionPane.showInputDialog(this,
                "Enter Date (YYYY-MM-DD or DD/MM/YYYY):",
                LocalDate.now().toString());

        if (input != null && !input.trim().isEmpty()) {
            try {
                LocalDate newDate;

                if (input.contains("/")) {
                    newDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } else {
                    newDate = LocalDate.parse(input);
                }

                Schedule newSchedule = new Schedule(System.currentTimeMillis(), newDate);
                company.addSchedule(newSchedule);

                newSchedule.addTimeSlot(new TimeSlot(LocalTime.of(9, 0), LocalTime.of(10, 0)));

                refreshTableData();

                JOptionPane.showMessageDialog(this,
                        "Date " + newDate + " added successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid date format. Use YYYY-MM-DD",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Adds a new time slot to the latest schedule.
     */
    private void addNewSlot() {
        if (company.getSchedules() == null || company.getSchedules().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a Date first.");
            return;
        }

        JTextField startF = new JTextField("10:00");
        JTextField endF = new JTextField("11:00");

        Object[] fields = {"Start Time:", startF, "End Time:", endF};

        int res = JOptionPane.showConfirmDialog(this, fields,
                "New Time Slot", JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.OK_OPTION) {
            try {
                LocalTime s = LocalTime.parse(startF.getText());
                LocalTime e = LocalTime.parse(endF.getText());

                Schedule lastSchedule =
                        company.getSchedules().get(company.getSchedules().size() - 1);

                lastSchedule.addTimeSlot(new TimeSlot(s, e));

                refreshTableData();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Time format must be HH:mm");
            }
        }
    }

    /**
     * Refreshes table data from company schedules.
     */
    private void refreshTableData() {
        tableModel.setRowCount(0);

        if (company.getSchedules() != null) {
            for (Schedule s : company.getSchedules()) {
                for (TimeSlot ts : s.getAllTimeSlots()) {
                    tableModel.addRow(new Object[]{
                            ts.getStartTime(),
                            ts.getEndTime(),
                            ts.isAvailable() ? "Available" : "Booked",
                            s.getDate()
                    });
                }
            }
        }
    }

    /**
     * Opens the appointment booking UI for the selected time slot.
     */
    private void openBookingUI() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a slot first!");
            return;
        }

        TimeSlot selectedSlot = null;
        int currentRow = 0;

        for (Schedule s : company.getSchedules()) {
            for (TimeSlot ts : s.getAllTimeSlots()) {

                if (currentRow == row) {
                    selectedSlot = ts;
                    selectedSlot.setSchedule(s);
                    break;
                }

                currentRow++;
            }
            if (selectedSlot != null) break;
        }

        if (selectedSlot != null) {
            new AppointmentUI(selectedSlot).setVisible(true);
        }
    }

    /**
     * Applies styling to JTable.
     *
     * @param table the table to style
     */
    private void styleTable(JTable table) {
        table.setRowHeight(35);
        table.setGridColor(new Color(230, 230, 230));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
    }

    /**
     * Creates a styled JButton.
     *
     * @param text button text
     * @param bg background color
     * @param fg text color
     * @return styled JButton
     */
    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton b = new JButton(text);
        b.setPreferredSize(new Dimension(150, 40));
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return b;
    }
}