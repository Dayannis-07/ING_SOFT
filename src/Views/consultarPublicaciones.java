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

public class consultarPublicaciones {
    private JFrame frame;
    private JPanel panel;

    public consultarPublicaciones() {
        initializeFrame();
        initializeHeaderAndFooter();
        initializeMainPanel();
        addTitleToPanel();
        createPosts();

        // Hacer visible el frame
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Consultar Posts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
    }

    private void initializeHeaderAndFooter() {
        // Añadir el header y footer usando HeaderFactory y FooterFactory
        JPanel header = HeaderFactory.createHeader();
        JPanel footer = FooterFactory.createBottomPanel();
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
    }

    private void initializeMainPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);
        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
    }

    private void addTitleToPanel() {
        JPanel titulo = new JPanel(new BorderLayout());
        titulo.setBackground(Color.WHITE);

        JLabel lblConsultarPosts = new JLabel("Consultar posts");
        lblConsultarPosts.setFont(new Font("Arial", Font.BOLD, 20));

        titulo.add(lblConsultarPosts, BorderLayout.WEST);
        panel.add(titulo);
    }

    private void createPosts() {
        String[][] posts = {
            {"Título Post 1", "2023-10-01", "Ubicación 1"},
            {"Título Post 2", "2023-10-02", "Ubicación 2"},
            {"Título Post 3", "2023-10-03", "Ubicación 3"},
            {"Título Post 4", "2023-10-04", "Ubicación 4"},
            {"Título Post 5", "2023-10-05", "Ubicación 5"},
            {"Título Post 6", "2023-10-06", "Ubicación 6"}
        };

        for (String[] post : posts) {
            JPanel pubPanel = createPostPanel(post);
            panel.add(pubPanel);
        }
    }

    private JPanel createPostPanel(String[] post) {
        JPanel pubPanel = new JPanel(new BorderLayout());
        pubPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        pubPanel.setBackground(Color.WHITE);

        JPanel leftPanel = createLeftPanel(post);
        JPanel rightPanel = createRightPanel();

        pubPanel.add(leftPanel, BorderLayout.WEST);
        pubPanel.add(rightPanel, BorderLayout.EAST);

        return pubPanel;
    }

    private JPanel createLeftPanel(String[] post) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Título: " + post[0]);
        JLabel dateLabel = new JLabel("Fecha: " + post[1]);
        JLabel locationLabel = new JLabel("Ubicación: " + post[2]);
        JButton viewButton = createViewButton(post[0]);

        leftPanel.add(titleLabel);
        leftPanel.add(dateLabel);
        leftPanel.add(locationLabel);
        leftPanel.add(viewButton);

        return leftPanel;
    }

    private JButton createViewButton(String postTitle) {
        JButton viewButton = new JButton("Ver más") {
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
                JOptionPane.showMessageDialog(frame, "Mostrando detalles del post: " + postTitle);
            }
        });

        return viewButton;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(Color.WHITE);

        rightPanel.add(centerPanel, BorderLayout.CENTER);

        return rightPanel;
    }

    public static void main(String[] args) {
        new consultarPublicaciones();
    }
}


