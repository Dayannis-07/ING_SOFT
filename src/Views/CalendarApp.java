import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarApp {
    private JFrame frame;
    private JLabel monthLabel;
    private JPanel[] dayPanels;
    private int month, year;
    private JPanel calendarPanel;
    private JLabel nextMonthLabel;
    private JLabel prevMonthLabel;
    Calendar cal;

    public CalendarApp() {
        initializeFrame();
        initializeHeaderAndFooter();
        initializeCalendarPanel();
        initializeMonthPanel();
        initializeDaysPanel();
        initializeCalendar();

        // Hacer visible el frame
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Calendario");
        frame.setTitle("Calendario");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
    }

    private void initializeHeaderAndFooter() {
        // Añadir el header y footer usando HeaderFactory y FooterFactory
        JPanel header = new HeaderFactory(frame).createHeader();
        JPanel footer = new FooterFactory(frame).createBottomPanel();
        //JPanel footer = FooterFactory.createBottomPanel();
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
    }

    private void initializeCalendarPanel() {
        calendarPanel = new JPanel(new BorderLayout());
        frame.add(calendarPanel, BorderLayout.CENTER);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                calendarPanel.setBounds(10, 100, frame.getWidth() - 40, frame.getHeight() - 150);
                calendarPanel.revalidate();
                calendarPanel.repaint();
            }
        });
    }

    private void initializeMonthPanel() {
        JPanel monthPanel = createMonthPanel();
        calendarPanel.add(monthPanel, BorderLayout.NORTH);
    }

    private void initializeDaysPanel() {
        JPanel daysPanel = createDaysPanel();
        calendarPanel.add(daysPanel, BorderLayout.CENTER);
    }

    private void initializeCalendar() {
        cal = new GregorianCalendar();
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        updateCalendar();
    }

    private JPanel createMonthPanel() {
        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        monthLabel = createMonthLabel();
        monthPanel.add(monthLabel);
        createMonthButtons(monthPanel);
        return monthPanel;
    }

    private JLabel createMonthLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        return label;
    }

    private void createMonthButtons(JPanel monthPanel) {
        ImageIcon nextIcon = new ImageIcon("../Assets/right_arrow.png");
        ImageIcon prevIcon = new ImageIcon("../Assets/left_arrow.png");

        Image nextImage = nextIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image prevImage = prevIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        nextIcon = new ImageIcon(nextImage);
        prevIcon = new ImageIcon(prevImage);

        nextMonthLabel = new JLabel(nextIcon);
        nextMonthLabel.setBounds(200, 60, 50, 50);
        prevMonthLabel = new JLabel(prevIcon);
        prevMonthLabel.setBounds(20, 60, 50, 50);

        monthPanel.add(prevMonthLabel);
        monthPanel.add(monthLabel);
        monthPanel.add(nextMonthLabel);

        nextMonthLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                nextMonth();
            }
        });

        prevMonthLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                prevMonth();
            }
        });
    }

    private JPanel createDaysPanel() {
        JPanel daysPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        addDayLabels(daysPanel, gbc);
        addDayPanels(daysPanel, gbc);
        return daysPanel;
    }

    private void addDayLabels(JPanel daysPanel, GridBagConstraints gbc) {
        String[] days = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (int i = 0; i < days.length; i++) {
            JLabel dayLabel = new JLabel(days[i], SwingConstants.CENTER);
            dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            dayLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1;
            daysPanel.add(dayLabel, gbc);
        }
    }

    private void addDayPanels(JPanel daysPanel, GridBagConstraints gbc) {
        dayPanels = new JPanel[42];
        for (int i = 0; i < 42; i++) {
            dayPanels[i] = new JPanel();
            dayPanels[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            dayPanels[i].setLayout(new BorderLayout());
            JLabel dayLabel = new JLabel("", SwingConstants.RIGHT);
            dayLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            dayPanels[i].add(dayLabel, BorderLayout.NORTH);
            gbc.gridx = i % 7;
            gbc.gridy = (i / 7) + 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.ipady = 15;
            daysPanel.add(dayPanels[i], gbc);
        }
    }

    private void nextMonth() {
        month++;
        if (month > 11) {
            month = 0;
            year++;
        }
        updateCalendar();
    }

    private void prevMonth() {
        month--;
        if (month < 0) {
            month = 11;
            year--;
        }
        updateCalendar();
    }

    private void cleanDaysPanels() {
        for (int i = 0; i < 42; i++) {
            JLabel label = (JLabel) dayPanels[i].getComponent(0);
            label.setText("");
        }
    }

    private void updateCalendar() {
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        monthLabel.setText(months[month] + " " + year);

        Calendar cal = new GregorianCalendar(year, month, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if (startDay < 0) {
            startDay += 7;
        }

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cleanDaysPanels();

        for (int i = 0; i < daysInMonth; i++) {
            JLabel label = (JLabel) dayPanels[i + startDay].getComponent(0);
            label.setText(String.valueOf(i + 1));
        }
    }

    public static void main(String[] args) {
        new CalendarApp();
    }
}

