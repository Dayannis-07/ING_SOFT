import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class verificarPublicaciones {
    public verificarPublicaciones() {
        // Crear el marco
        JFrame frame = new JFrame("Verificar Evento");
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
        ImageIcon iconNotificationImg = new ImageIcon("Proyecto/Proyecto/notification_icon2.png");
        ImageIcon iconCalendarImg = new ImageIcon("Proyecto/Proyecto/calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("Proyecto/Proyecto/home_icon2.png");
        ImageIcon iconProfileImg = new ImageIcon("Proyecto/Proyecto/profile_icon2.png");
        ImageIcon iconLogOutImg = new ImageIcon("Proyecto/Proyecto/logout_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("Proyecto/Proyecto/bh_icon.jpeg");

        // Escalar imágenes a 40x40
        Image imgNotification = iconNotificationImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgLogOut = iconLogOutImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        // Crear JLabels con los íconos escalados
        JLabel iconNotification = new JLabel(new ImageIcon(imgNotification));
        JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
        JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
        JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
        JLabel iconLogOut = new JLabel(new ImageIcon(imgLogOut));
        JLabel iconBH = new JLabel(new ImageIcon(imgBH));

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
                JOptionPane.showMessageDialog(frame, "Redirigiendo a Notificaciones...");
            }
        });

        iconCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo al Calendario...");
            }
        });

        iconHomepage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo a la Página Principal...");
            }
        });

        iconProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo al Perfil...");
            }
        });

        // Acción para cerrar sesión
        iconLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int confirm = JOptionPane.showConfirmDialog(frame, 
                    "¿Seguro que deseas cerrar sesión?", 
                    "Cerrar Sesión", 
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
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

        JPanel titulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titulo.setBackground(Color.WHITE);

        JLabel lblVerificarPublicaciones = new JLabel("Verificar publicaciones");
        lblVerificarPublicaciones.setFont(new Font("Arial", Font.BOLD, 20));
        // lblVerificarPublicaciones.setForeground(new Color(90, 90, 90)); // Gris
        // oscuro

        titulo.add(lblVerificarPublicaciones);
        panel.add(titulo);

        // Crear publicaciones
        String[][] publicaciones = {
                { "Título Publicación 1", "2023-10-01", "Ubicación 1" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 3", "2023-10-03", "Ubicación 3" }
        };

        for (String[] publicacion : publicaciones) {
            JPanel pubPanel = new JPanel(new BorderLayout());
            pubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            pubPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
            ));
            pubPanel.setBackground(Color.WHITE);
            // JPanel pubPanel = new JPanel(new BorderLayout());
            // pubPanel.setBorder(BorderFactory.createCompoundBorder(
            // BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            // BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
            // ));

            // Panel izquierdo para detalles de la publicación
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel("Título: " + publicacion[0]);
            JLabel dateLabel = new JLabel("Fecha: " + publicacion[1]);
            JLabel locationLabel = new JLabel("Ubicación: " + publicacion[2]);
            JButton viewButton = new JButton("Ver Publicación");

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

            JButton acceptButton = new JButton("Aceptar");
            JButton denyButton = new JButton("Denegar");

            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Publicación aceptada: " + publicacion[0]);
                }
            });

            denyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Publicación denegada: " + publicacion[0]);
                }
            });

            centerPanel.add(acceptButton);
            centerPanel.add(denyButton);

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
        bottomPanel.setPreferredSize(new Dimension(700, 30));

        // Agregar todo al marco
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Mostrar ventana
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new verificarPublicaciones();
    }
}