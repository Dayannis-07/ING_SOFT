package main.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarPublicacionesView extends JDialog {
    private JTextField fechaField;
    private JTextField tituloField;
    private JButton buscarButton;

    public BuscarPublicacionesView(JFrame parent) {
        super(parent, "Buscar Publicación", true);
        initializeComponents();
    }

    private void initializeComponents() {
        // Configurar el layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de texto para la fecha
        JLabel fechaLabel = new JLabel("Fecha de publicación:");
        fechaLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fechaLabel, gbc);

        fechaField = new JTextField("dd/mm/yyyy", 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fechaField, gbc);

        // Campo de texto para el título
        JLabel tituloLabel = new JLabel("Título:");
        tituloLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(tituloLabel, gbc);

        tituloField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tituloField, gbc);

        // Botón de búsqueda
        buscarButton = new JButton("Buscar");
        buscarButton.setBackground(Color.LIGHT_GRAY);  // Set button color to grey
        buscarButton.setForeground(Color.BLACK);  // Set text color to black
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;  // Center the button
        add(buscarButton, gbc);

        // Configurar el tamaño y visibilidad del pop-up
        setSize(600, 200);
        setLocationRelativeTo(getParent());
    }

    public String getFecha() {
        return fechaField.getText();
    }

    public String getTitulo() {
        return tituloField.getText();
    }

    public void addBuscarListener(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }
}
