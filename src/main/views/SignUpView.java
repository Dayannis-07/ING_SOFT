package main.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.utils.*;
import main.controllers.signUpController;
import main.components.Footer;
import main.components.HeaderSignUp;
import main.components.RoundedButton;

public class SignUpView {
    private JFrame frame;
    private JPanel panel;
    private JPanel grayPanel;

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtName;
    private JTextField txtLastName;
    private JComboBox<String> comboUserType;

    private signUpController controller;

    public SignUpView() {
        controller = new signUpController();

        createFrame();
        initializeHeaderAndFooter();
        createMainPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Registrarse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Size.FRAME_SIZE);
        frame.setLayout(new BorderLayout());
    }

    private void initializeHeaderAndFooter() {
        // Añadir el header y footer
        JPanel header = new HeaderSignUp(frame);
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
        grayPanel.setPreferredSize(Size.GRAY_PANEL_REGISTER_SIZE);

        addRegisterTitle();
        addEmailField();
        addPasswordFields();
        addUserTypeDropdown();
        addRegisterButton();
        addNameField();
        addLastNameFields();
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
        gbc.insets = new Insets(0, 0, 30, 392);

        grayPanel.add(lblRegisterTitle, gbc);
    }

    private void addEmailField() {
        JLabel lblEmail = new JLabel("Correo Electronico:");
        txtEmail = new JTextField(20);
        txtEmail.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 7, 300);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblEmail, gbc);

        gbc.gridy = 2;
        grayPanel.add(txtEmail, gbc);
    }

    private void addNameField() {
        JLabel lblName = new JLabel("Nombre:");
        txtName = new JTextField(20);
        txtName.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 300, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblName, gbc);

        gbc.gridy = 2;
        grayPanel.add(txtName, gbc);
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
        gbc.insets = new Insets(5, 10, 7, 300);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblPassword, gbc);

        gbc.gridy = 4;
        grayPanel.add(txtPassword, gbc);

        gbc.gridy = 5;
        grayPanel.add(lblConfirmPassword, gbc);

        gbc.gridy = 6;
        grayPanel.add(txtConfirmPassword, gbc);
    }

    private void addLastNameFields() {
        JLabel lblLastName = new JLabel("Apellido:");
        txtLastName = new JTextField(20);
        txtLastName.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 300, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblLastName, gbc);

        gbc.gridy = 4;
        grayPanel.add(txtLastName, gbc);

    }

    private void addUserTypeDropdown() {
        JLabel lblUserType = new JLabel("Tipo de Usuario:");
        String[] userTypes = {"Profesor", "Estudiante", "Personal Administrativo", "Personal Obrero"};
        comboUserType = new JComboBox<>(userTypes);
        comboUserType.setBackground(Palette.instance().getWhite());
        comboUserType.setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 300, 7, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        grayPanel.add(lblUserType, gbc);

        gbc.gridy = 6;
        grayPanel.add(comboUserType, gbc);
    }

    private void addRegisterButton() {
        RoundedButton btnRegister = new RoundedButton("Registrarse", Palette.instance().getLightGreen());

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
                String Name = txtName.getText();
                String LastName = txtLastName.getText();

               String message = controller.registerUser(email, password, confirmPassword, userType, Name, LastName);
                if (message.contains("Registro exitoso")) {
                    JOptionPane.showMessageDialog(frame, message, "Exito", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LogInView();
                }else{
                    showErrorMessage(message);
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(35, 200, 0, 200);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        grayPanel.add(btnRegister, gbc);
    }

    private void assembleFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void showErrorMessage(String message){
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}