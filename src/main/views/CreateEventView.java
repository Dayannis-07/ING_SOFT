package main.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.utils.Palette;
import main.utils.Size;
import main.components.FooterFactory;
import main.components.HeaderFactory;
import main.controllers.createEventController;

public class CreateEventView extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel grayPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JTextField txtEventTitle;
    private JTextField txtPlace;
    private JTextField txtDate;
    private JTextArea txtDescription;
    private JLabel selectedFile;

    public CreateEventView() {
        createFrame();
        initializeHeaderAndFooter();
        createMainPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Publicar Evento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Size.FRAME_SIZE);
        frame.setLayout(new BorderLayout());
    }

    private void initializeHeaderAndFooter() { 
        // Añadir el header y footer usando HeaderFactory y FooterFactory 
        JPanel header = HeaderFactory.createHeader(); 
        JPanel footer = FooterFactory.createBottomPanel(); 
        frame.add(header, BorderLayout.NORTH); 
        frame.add(footer, BorderLayout.SOUTH); 
    }

    private void createMainPanel() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Palette.instance().getBeige(), 2, true));
        panel.setBackground(Palette.instance().getWhite());

        createGrayPanel();
        panel.add(grayPanel, new GridBagConstraints());
    }

    private void createGrayPanel() {
        grayPanel = new JPanel(new GridBagLayout());
        grayPanel.setBackground(Palette.instance().getLightGray());
        grayPanel.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        grayPanel.setPreferredSize(Size.GRAY_PANEL_SIZE);

        addCreateEventLabel();
        createLeftPanel();

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;
        gbcLeft.anchor = GridBagConstraints.NORTHWEST;
        gbcLeft.insets = new Insets(10, 20, 10, 10);
        grayPanel.add(leftPanel, gbcLeft);

        createRightPanel();
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 1;
        gbcRight.gridy = 1;
        gbcRight.anchor = GridBagConstraints.NORTHWEST;
        gbcRight.insets = new Insets(10, 10, 10, 20);
        grayPanel.add(rightPanel, gbcRight);

        addSubmitButton();
    }

    private void addCreateEventLabel() {
        JLabel lblCreateEvent = new JLabel("Crear evento");
        lblCreateEvent.setFont(new Font("Arial", Font.BOLD, 20));
        lblCreateEvent.setForeground(Palette.instance().getGray());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 20, 20, 20);
        grayPanel.add(lblCreateEvent, gbc);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Palette.instance().getLightGray());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEventTitle = new JLabel("Título del evento:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(lblEventTitle, gbc);

        txtEventTitle = new JTextField(20);
        txtEventTitle.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        gbc.gridy = 1;
        leftPanel.add(txtEventTitle, gbc);

        JLabel lblPlace = new JLabel("Lugar:");
        gbc.gridy = 2;
        leftPanel.add(lblPlace, gbc);

        txtPlace = new JTextField(20);
        txtPlace.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        gbc.gridy = 3;
        leftPanel.add(txtPlace, gbc);

        JLabel lblFile = new JLabel("Subir archivo:");
        gbc.gridy = 4;
        leftPanel.add(lblFile, gbc);

        JButton btnFile = new JButton("Elegir archivo");
        btnFile.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        gbc.gridy = 5;
        leftPanel.add(btnFile, gbc);

        selectedFile = new JLabel("Ningún archivo seleccionado");
        gbc.gridy = 6;
        leftPanel.add(selectedFile, gbc);

        btnFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int selection = fileChooser.showOpenDialog(null);

                if (selection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    selectedFile.setText(filePath);
                } else {
                    selectedFile.setText("No se seleccionó ningún archivo.");
                }
            }
        });
    }

    private void createRightPanel() {
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Palette.instance().getLightGray());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblDate = new JLabel("Fecha:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(lblDate, gbc);

        txtDate = new JTextField(15);
        txtDate.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        gbc.gridy = 1;
        rightPanel.add(txtDate, gbc);

        JLabel lblDescription = new JLabel("Descripción:");
        gbc.gridy = 2;
        rightPanel.add(lblDescription, gbc);

        txtDescription = new JTextArea(7, 20);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane scrollDescription = new JScrollPane(txtDescription);
        scrollDescription.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        gbc.gridy = 3;
        rightPanel.add(scrollDescription, gbc);
    }

    private void addSubmitButton() {
        JButton btnSubmit = new JButton("Enviar") {
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

        btnSubmit.setPreferredSize(Size.BUTTON_SIZE);
        btnSubmit.setForeground(Palette.instance().getDarkGreen());
        btnSubmit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnSubmit.setContentAreaFilled(false);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEventController.submitEvent(txtEventTitle, txtPlace, txtDate, txtDescription, selectedFile, frame);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        grayPanel.add(btnSubmit, gbc);
    }

    private void assembleFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CreateEventView();
    }
}
