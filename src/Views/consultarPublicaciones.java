import javax.swing.*;

import utils.Palette;
import utils.Size;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class consultarPublicaciones extends Frame{
    public consultarPublicaciones() {
        // Crear el marco
        JFrame frame = new JFrame("Consultar Publicaciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        // Panel superior para el título y los iconos
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true)); 

        // Panel para los iconos (derecha)
        JPanel panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelIconos.setBackground(Color.WHITE);

        // Icono adicional a la izquierda
        JPanel panelIcon = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIcon.setBackground(Color.WHITE);

        // Redimensionar iconos
        ImageIcon iconNotificationImg = new ImageIcon("../Assets/notification_icon2.png");
        ImageIcon iconCalendarImg = new ImageIcon("../Assets/calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("../Assets/home_icon2.png");
        ImageIcon iconProfileImg = new ImageIcon("../Assets/profile_icon2.png");
        ImageIcon iconLogOutImg = new ImageIcon("../Assets/logout_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("../Assets/bh_icon.jpeg");
        ImageIcon iconSrchImg = new ImageIcon("../Assets/search_icon.png");

        // Escalar imágenes a 40x40
        Image imgNotification = iconNotificationImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgLogOut = iconLogOutImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgSrch = iconSrchImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        // Crear JLabels con los íconos escalados
        JLabel iconNotification = new JLabel(new ImageIcon(imgNotification));
        JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
        JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
        JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
        JLabel iconLogOut = new JLabel(new ImageIcon(imgLogOut));
        JLabel iconBH = new JLabel(new ImageIcon(imgBH));
        JLabel iconSrch = new JLabel(new ImageIcon(imgSrch));

        // Agregar íconos al panel derecho
        panelIconos.add(iconNotification);
        panelIconos.add(iconCalendar);
        panelIconos.add(iconHomepage);
        panelIconos.add(iconProfile);
        panelIconos.add(iconLogOut);
        panelIcon.add(iconBH);

        // Agregar funcionalidad de clic (simularemos redirección con mensajes)
        iconNotification.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo a Notificaciones...");
                frame.dispose();
                new verificarPublicaciones();
            }
        });

        iconCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo al Calendario...");
                frame.dispose();
                new CalendarApp();
            }
        });

        iconHomepage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo a la Página Principal...");
                frame.dispose();
                new consultarPublicaciones();
            }
        });

        iconProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo al Perfil...");
                frame.dispose();
                new userProfile();
            }
        });

        iconLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame,
                    "¿Seguro que deseas cerrar sesión?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new logIn();
                    
                }
            }
        });

        //Acción para buscar publicación
        iconSrch.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo a Búsqueda...");
            }
        });

        // Agregar paneles al superior
        topPanel.add(panelIconos, BorderLayout.EAST);
        topPanel.add(panelIcon, BorderLayout.WEST);

        // Panel principal (centro de la ventana)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel titulo = new JPanel();
        titulo.setLayout(new BorderLayout());
        titulo.setBackground(Color.WHITE);

        JLabel lblConsultarPublicaciones = new JLabel("Consultar publicaciones");
        lblConsultarPublicaciones.setFont(new Font("Arial", Font.BOLD, 20));

        titulo.add(lblConsultarPublicaciones, BorderLayout.WEST);
        titulo.add(iconSrch, BorderLayout.EAST);
        panel.add(titulo);

        // Crear publicaciones
        String[][] publicaciones = {
            {"Título Publicación 1", "2023-10-01", "Ubicación 1"},
            {"Título Publicación 2", "2023-10-02", "Ubicación 2"},
            {"Título Publicación 3", "2023-10-03", "Ubicación 3"},
            {"Título Publicación 4", "2023-10-04", "Ubicación 4"},
            {"Título Publicación 5", "2023-10-05", "Ubicación 5"},
            {"Título Publicación 6", "2023-10-06", "Ubicación 6"}
        };

        for (String[] publicacion : publicaciones) {
            JPanel pubPanel = new JPanel(new BorderLayout());
            pubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            pubPanel.setBorder(BorderFactory.createCompoundBorder(
                 BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                 BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
            ));
            pubPanel.setBackground(Color.WHITE);
            //             JPanel pubPanel = new JPanel(new BorderLayout());
            // pubPanel.setBorder(BorderFactory.createCompoundBorder(
            //     BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            //     BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
            // ));

            // Panel izquierdo para detalles de la publicación
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel("Título: " + publicacion[0]);
            JLabel dateLabel = new JLabel("Fecha: " + publicacion[1]);
            JLabel locationLabel = new JLabel("Ubicación: " + publicacion[2]);
            JButton viewButton = new JButton("Ver más"){
                @Override
                protected void paintComponent(Graphics g) {
                    if (!isOpaque()) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Palette.instance().getDarkGray());
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                        g2.dispose();
                    }
                    super.paintComponent(g);
                }
            };

            viewButton.setPreferredSize(Size.BUTTON_SIZE); 
            viewButton.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
            viewButton.setContentAreaFilled(false);

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Mostrando detalles de la publicación: " + publicacion[0]);
                }
            });

            leftPanel.add(titleLabel);
            leftPanel.add(dateLabel);
            leftPanel.add(locationLabel);
            leftPanel.add(viewButton);

            // Panel derecho para botones de aceptar y denegar
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.setBackground(Color.WHITE);

            // Panel para central los botones
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
            centerPanel.setBackground(Color.WHITE);

            rightPanel.add(centerPanel, BorderLayout.CENTER);

            // Agregar paneles izquierdo y derecho al panel de publicación
            pubPanel.add(leftPanel, BorderLayout.WEST);
            pubPanel.add(rightPanel, BorderLayout.EAST);

            // Agregar el panel de publicación al panel principal
            panel.add(pubPanel);
        }

        // Panel inferior (Beige)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(222, 196, 145)); // Beige
        bottomPanel.setPreferredSize(new Dimension(800, 45));
        ImageIcon iconAddImg = new ImageIcon("../Assets/add_icon.png");

        Image imgAdd = iconAddImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconAdd = new JLabel(new ImageIcon(imgAdd));

        bottomPanel.add(iconAdd);

        iconAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Agregar evento...");
                frame.dispose();
                new createEvent();
            }
        });

        // Agregar todo al marco
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Mostrar ventana
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new consultarPublicaciones();
    }
}
