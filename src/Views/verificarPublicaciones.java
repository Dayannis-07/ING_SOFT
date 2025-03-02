import javax.swing.*;

import utils.Palette;
import utils.Size;
import utils.HeaderFactory;
import utils.FooterFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class verificarPublicaciones extends JFrame{

    JPanel panel;

    public verificarPublicaciones() {
        // set up frame options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Size.FRAME_SIZE); 
        setLayout(new BorderLayout());

        // build sections
        JPanel header = new HeaderFactory(this).createHeader();
        JPanel footer = new FooterFactory(this).createBottomPanel();
        //JPanel footer = FooterFactory.createBottomPanel();

        createMainPanel();

        // add secctions
        add(header, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void createMainPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Verificar Publicaciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBackground(Palette.instance().getWhite());

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

        JPanel publicationsPanel = new JPanel();
        publicationsPanel.setLayout(new BoxLayout(publicationsPanel, BoxLayout.Y_AXIS));
        publicationsPanel.setBackground(Palette.instance().getWhite());

        for (String[] publicacion : publicaciones) {
            JPanel pubPanel = new JPanel(new BorderLayout());
            pubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            pubPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
            ));
            pubPanel.setBackground(Color.WHITE);

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
                    JOptionPane.showMessageDialog(panel, "Mostrando detalles de la publicación: " + publicacion[0]);
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
            centerPanel.setLayout(new BorderLayout(10, 20));
            centerPanel.setBackground(Color.WHITE);

            ImageIcon iconCheckImg = new ImageIcon("../Assets/check.png");
            ImageIcon iconXImg = new ImageIcon("../Assets/remove.png");

            Image imgCheck = iconCheckImg.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            Image imgX = iconXImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            JLabel iconCheck = new JLabel(new ImageIcon(imgCheck));
            JLabel iconX = new JLabel(new ImageIcon(imgX));

            iconCheck.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(panel, "Publicación aceptada: " + publicacion[0]);
                }
            });

            iconX.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(panel, "Publicación denegada: " + publicacion[0]);
                }
            });

            centerPanel.add(iconCheck, BorderLayout.WEST);
            centerPanel.add(iconX, BorderLayout.EAST);

            rightPanel.add(centerPanel, BorderLayout.CENTER);

            // Agregar paneles izquierdo y derecho al panel de publicación
            pubPanel.add(leftPanel, BorderLayout.WEST);
            pubPanel.add(rightPanel, BorderLayout.EAST);

            // Agregar el panel de publicación al panel principal
            publicationsPanel.add(pubPanel);
        }

        JScrollPane publicationsScrollPanel = new JScrollPane(publicationsPanel);

        panel.add(publicationsScrollPanel);
    }

    public static void main(String[] args) {
        new verificarPublicaciones();
    }
}