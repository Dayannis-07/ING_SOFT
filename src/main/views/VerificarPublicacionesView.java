package main.views;

import javax.swing.*;

import main.utils.Palette;
import main.utils.Size;
import main.components.HeaderFactory;
import main.components.VerifyPostPanel;
import main.components.FooterFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;

import main.controllers.consultarPublicacionesController;
import main.controllers.verifyPostController;
import main.models.Post;

public class VerificarPublicacionesView extends JFrame {

    private JPanel panel;
    verifyPostController controller;

    public VerificarPublicacionesView() {

        controller = new verifyPostController(false);

        // set up frame options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Size.FRAME_SIZE);
        setLayout(new BorderLayout());

        // initialize header and footer
        initializeHeaderAndFooter();

        // initialize main panel
        createMainPanel();

        // make frame visible and center it on the screen
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initializeHeaderAndFooter() {
        // Add the header and footer using HeaderFactory and FooterFactory
        JPanel header = HeaderFactory.createHeader();
        JPanel footer = FooterFactory.createBottomPanel();
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
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

        createPublicationsPanel();

        JScrollPane publicationsScrollPanel = new JScrollPane(panel);
        add(publicationsScrollPanel, BorderLayout.CENTER);
    }

    private void createPublicationsPanel() {
        ArrayList<Post> publicaciones = controller.getPosts();

        JPanel publicationsPanel = new JPanel();
        publicationsPanel.setLayout(new BoxLayout(publicationsPanel, BoxLayout.Y_AXIS));
        publicationsPanel.setBackground(Palette.instance().getWhite());

        for (Post publicacion : publicaciones) {
            JPanel pubPanel = new VerifyPostPanel(publicacion, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(panel, "Publicación aceptada: " + publicacion.getTitle());
                    controller.checkPost(publicacion.getId());

                    panel.remove(publicationsPanel);
                    panel.revalidate();
                    panel.repaint();
                    createPublicationsPanel();
                }
            }, new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(panel, "Publicación denegada: " + publicacion.getTitle());
                    System.out.println("Deliting post " + publicacion.getId());
                    controller.denyPost(publicacion.getId());

                    panel.remove(publicationsPanel);
                    panel.revalidate();
                    panel.repaint();
                    createPublicationsPanel();
                }
            });
            publicationsPanel.add(pubPanel);
        }

        panel.add(publicationsPanel);
    }

    public static void main(String[] args) {
        new VerificarPublicacionesView();
    }

}
