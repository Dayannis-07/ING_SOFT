import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarApp extends JFrame {
    private JLabel monthLabel;
    private JPanel[] dayPanels;
    private int month, year;
    private JPanel calendarPanel;
    private JLabel nextMonthLabel;
    private JLabel prevMonthLabel;

    public CalendarApp() {
        setTitle("Calendario");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        // Configurar la etiqueta del mes y año
        monthLabel = new JLabel();
        monthLabel.setFont(new Font("SansSerif", Font.PLAIN, 18)); 
        monthLabel.setBounds(70, 60, 200, 50);
        add(monthLabel);

        // Cargar y redimensionar íconos
        ImageIcon nextIcon = new ImageIcon("src/Assets/right_arrow.png");
        ImageIcon prevIcon = new ImageIcon("src/Assets/left_arrow.png");

        Image nextImage = nextIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image prevImage = prevIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        nextIcon = new ImageIcon(nextImage);
        prevIcon = new ImageIcon(prevImage);

        // Etiqueta para avanzar al siguiente mes
        nextMonthLabel = new JLabel(nextIcon);
        nextMonthLabel.setBounds(200, 60, 50, 50); // Coordenadas personalizadas
        add(nextMonthLabel);

        // Etiqueta para retroceder al mes anterior
        prevMonthLabel = new JLabel(prevIcon);
        prevMonthLabel.setBounds(20, 60, 50, 50); // Coordenadas personalizadas
        add(prevMonthLabel);

        // Panel para los días del mes
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(7, 7, 1, 1)); 
        calendarPanel.setBounds(10, 100, getWidth() - 40, getHeight() - 150); 
        add(calendarPanel);

        // Añadir etiquetas para los días de la semana
        String[] days = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Usa fuente SansSerif
            calendarPanel.add(dayLabel);
        }

        // Añadir paneles para los días del mes
        dayPanels = new JPanel[42];
        for (int i = 0; i < 42; i++) {
            dayPanels[i] = new JPanel();
            dayPanels[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Añadir borde para diferenciar los días
            dayPanels[i].setLayout(new BorderLayout());
            dayPanels[i].add(new JLabel("", SwingConstants.RIGHT), BorderLayout.NORTH); // Añadir etiqueta para el número del día
            calendarPanel.add(dayPanels[i]);
        }

        // Obtener el mes y año actual
        Calendar cal = new GregorianCalendar();
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        // Actualizar el calendario
        updateCalendar();

        // Añadir listener para redimensionar el frame
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                calendarPanel.setBounds(10, 100, getWidth() - 40, getHeight() - 150);
                calendarPanel.revalidate();
                calendarPanel.repaint();
            }
        });

        // Añadir listener para avanzar al siguiente mes
        nextMonthLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                nextMonth();
            }
        });

        // Añadir listener para retroceder al mes anterior
        prevMonthLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                prevMonth();
            }
        });

        // Hacer visible el frame
        setVisible(true);
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

    private void updateCalendar() {
        // Actualizar la etiqueta del mes y año
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        monthLabel.setText(months[month] + " " + year);

        // Obtener el primer día del mes
        Calendar cal = new GregorianCalendar(year, month, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
        if (startDay < 0) {
            startDay += 7;
        }

        // Obtener el número de días en el mes
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Limpiar paneles de días
        for (int i = 0; i < 42; i++) {
            JLabel label = (JLabel) dayPanels[i].getComponent(0);
            label.setText("");
        }

        // Rellenar paneles con los días del mes
        for (int i = 0; i < daysInMonth; i++) {
            JLabel label = (JLabel) dayPanels[i + startDay].getComponent(0);
            label.setText(String.valueOf(i + 1));
        }
        //probando un commit
        pruebaCommit();
    }
    private void pruebaCommit(){}
    public static void main(String[] args) {
        new CalendarApp();
    }
}



