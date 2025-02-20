import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.Palette;
import utils.Size;
import controllers.signInController;

public class signIn {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel panelIconos;
    private JPanel panelIcon;
    private JPanel panel;
    private JPanel grayPanel;
    private JPanel bottomPanel;

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JComboBox <String> comboUserType;

    public signIn() {
        createFrame();
        createTopPanel();
        createMainPanel();
        createBottomPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Registrarse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Size.FRAME_SIZE); 
        frame.setLayout(new BorderLayout());
    }

    private void createTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Palette.instance().getWhite());
        topPanel.setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));
        topPanel.setPreferredSize(Size.TOP_PANEL_SIZE); 

        panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelIconos.setBackground(Palette.instance().getWhite());
        panelIconos.setPreferredSize(Size.PANEL_ICONOS_SIZE); 

        panelIcon = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIcon.setBackground(Palette.instance().getWhite());
        panelIcon.setPreferredSize(Size.PANEL_ICON_SIZE); 

        addIconsToTopPanel();
        topPanel.add(panelIconos, BorderLayout.EAST);
        topPanel.add(panelIcon, BorderLayout.WEST);
    }

    private void addIconsToTopPanel() {
        ImageIcon iconCalendarImg = new ImageIcon("calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("home_icon2.png");
        ImageIcon iconProfileImg = new ImageIcon("profile_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("bh_icon.jpeg");

        Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
        JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
        JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
        JLabel iconBH = new JLabel(new ImageIcon(imgBH));

        panelIconos.add(iconCalendar);
        panelIconos.add(iconHomepage);
        panelIconos.add(iconProfile);
        panelIcon.add(iconBH);

        addIconListeners(iconCalendar, iconHomepage, iconProfile);
    }

    private void addIconListeners(JLabel iconCalendar, JLabel iconHomepage, JLabel iconProfile) {
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
    }

    private void createMainPanel() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Palette.instance().getBeige(), 2, true));
        panel.setBackground(Palette.instance().getWhite());
        panel.setPreferredSize(Size.PANEL_SIZE);

        createGrayPanel();
        panel.add(grayPanel, new GridBagConstraints());
    }

    private void createGrayPanel() {
        grayPanel = new JPanel(new GridBagLayout());
        grayPanel.setBackground(Palette.instance().getLightGray());
        grayPanel.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        grayPanel.setPreferredSize(Size.GRAY_PANEL_REGISTER_SIZE); 

        addRegisterTitle();
        addEmailField();
        addPasswordFields();
        addUserTypeDropdown();
        addRegisterButton();
    }

    private void addRegisterTitle() {
        JLabel lblRegisterTitle = new JLabel("Crear cuenta");
        lblRegisterTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblRegisterTitle.setForeground(Palette.instance().getGray());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 20, 0);

        grayPanel.add(lblRegisterTitle, gbc);
    }

    private void addEmailField() {
        JLabel lblEmail = new JLabel("Correo Electrónico:");
        txtEmail = new JTextField(20);
        txtEmail.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblEmail, gbc);

        gbc.gridy = 2;
        grayPanel.add(txtEmail, gbc);
    }

    private void addPasswordFields() {
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);
        txtPassword.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        JLabel lblConfirmPassword = new JLabel("Confirmar Contraseña:");
        txtConfirmPassword = new JPasswordField(20);
        txtConfirmPassword.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblPassword, gbc);

        gbc.gridy = 4;
        grayPanel.add(txtPassword, gbc);

        gbc.gridy = 5;
        grayPanel.add(lblConfirmPassword, gbc);

        gbc.gridy = 6;
        grayPanel.add(txtConfirmPassword, gbc);
    }

    private void addUserTypeDropdown() {
        JLabel lblUserType = new JLabel("Tipo de Usuario:");
        String[] userTypes = {"Profesor", "Estudiante", "Personal Administrativo", "Personal Obrero"};
        comboUserType = new JComboBox<>(userTypes);
        comboUserType.setBackground(Palette.instance().getWhite());
        comboUserType.setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblUserType, gbc);

        gbc.gridy = 8;
        grayPanel.add(comboUserType, gbc);
    }

    private void addRegisterButton() {
        JButton btnRegister = new JButton("Registrarse") {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Palette.instance().getLightGreen());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        btnRegister.setPreferredSize(Size.BUTTON_SIZE); 
        btnRegister.setForeground(Palette.instance().getDarkGreen());
        btnRegister.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btnRegister.setContentAreaFilled(false);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                String userType = (String) comboUserType.getSelectedItem();
        
                String message = signInController.registerUser(email, password, confirmPassword, userType);
                JOptionPane.showMessageDialog(frame, message, 
                        message.equals("Registro exitoso.") ? "Éxito" : "Error",
                        message.equals("Registro exitoso.") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(35, 60, 0, 60);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        grayPanel.add(btnRegister, gbc);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Palette.instance().getBeige());
        bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 
    }

    private void assembleFrame() {
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new signIn();
    }
}