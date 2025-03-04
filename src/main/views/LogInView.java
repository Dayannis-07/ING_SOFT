package main.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.utils.Palette;
import main.utils.Size;
import main.components.Footer;
import main.components.HeaderLogIn;
import main.controllers.logInController;

public class LogInView extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel grayPanel;

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    private logInController controller;

    public static String userEmail;

    public LogInView() {
        controller = new logInController();

        createFrame();
        initializeHeaderAndFooter();
        createMainPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Iniciar Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Size.FRAME_SIZE);
        frame.setLayout(new BorderLayout());
    }

    private void initializeHeaderAndFooter() {
        // Añadir el header y footer 
        JPanel header = new HeaderLogIn();
        JPanel footer = new Footer();
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
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
        grayPanel.setPreferredSize(Size.GRAY_PANEL_LOGIN_SIZE);

        addLoginTitle();
        addEmailField();
        addPasswordField();
        addLoginButton();
        addRegisterButton();
    }

    private void addLoginTitle() {
        JLabel lblLoginTitle = new JLabel("Iniciar Sesión");
        lblLoginTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblLoginTitle.setForeground(Palette.instance().getGray());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 20, 0);

        grayPanel.add(lblLoginTitle, gbc);
    }

    private void addEmailField() {
        JLabel lblEmail = new JLabel("Correo electrónico:");
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

    private void addPasswordField() {
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);
        txtPassword.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblPassword, gbc);

        gbc.gridy = 4;
        grayPanel.add(txtPassword, gbc);
    }

    private void addLoginButton() {
        JButton btnLogin = new JButton("Ingresar") {
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
        btnLogin.setPreferredSize(Size.BUTTON_SIZE);
        btnLogin.setForeground(Palette.instance().getDarkGreen());
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        btnLogin.setContentAreaFilled(false);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                userEmail = email;

                String message = controller.validateCredentials(email, password);

                if (message.startsWith("Inicio")) {
                    if (controller.isAdmin(email)) {
                        JOptionPane.showMessageDialog(frame, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new VerificarPublicacionesView(); // Redirigir a la vista de administrador
                    } else {
                        JOptionPane.showMessageDialog(frame, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new ConsultarPublicacionesView(); // Redirigir a la vista de usuario normal
                    }
                } else {
                    showErrorMessage(message); 
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 60, 0, 60);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        grayPanel.add(btnLogin, gbc);
    }

    private void addRegisterButton() {
        JButton btnRegister = new JButton("Registrarse") {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Palette.instance().getTransparentBeige());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        btnRegister.setPreferredSize(Size.BUTTON_SIZE);
        btnRegister.setForeground(Palette.instance().getGray());
        btnRegister.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        btnRegister.setContentAreaFilled(false);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignInView(); // Redirigir a la vista de registro
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 60, 0, 60);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        grayPanel.add(btnRegister, gbc);
    }

    private void assembleFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new LogInView();
    }
}