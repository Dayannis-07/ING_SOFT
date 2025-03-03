package main.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.models.Post;
import main.utils.Palette;
import main.utils.Size;

public class VerifyPostPanel extends JPanel {
    Post post;
    MouseListener onCheck;
    MouseListener onDenny;

    public VerifyPostPanel(Post post, MouseListener onCheck, MouseListener onDenny) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);

        this.post = post;
        this.onCheck = onCheck;
        this.onDenny = onDenny;

        JPanel leftPanel = createPublicationDetailsPanel();

        JPanel rightPanel = createButtonsPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createPublicationDetailsPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Título: " + post.getTitle());
        JLabel dateLabel = new JLabel("Fecha: " + post.getDate());
        JLabel locationLabel = new JLabel("Ubicación: " + post.getPlace());
        JButton viewButton = createViewButton(post.getTitle());

        leftPanel.add(titleLabel);
        leftPanel.add(dateLabel);
        leftPanel.add(locationLabel);
        leftPanel.add(viewButton);

        return leftPanel;
    }

    private JButton createViewButton(String postTitle) {
        JPanel panel = this;
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
                JOptionPane.showMessageDialog(panel, "Mostrando detalles del post: " + postTitle);
            }
        });

        return viewButton;
    }

    private JPanel createButtonsPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 20));
        centerPanel.setBackground(Color.WHITE);

        ImageIcon iconCheckImg = loadIcon("/assets/check.png");
        ImageIcon iconXImg = loadIcon("/assets/remove.png");

        Image imgCheck = iconCheckImg.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        Image imgX = iconXImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconCheck = new JLabel(new ImageIcon(imgCheck));
        JLabel iconX = new JLabel(new ImageIcon(imgX));

        iconCheck.addMouseListener(onCheck);

        /*
         * new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(panel, "Publicación aceptada: " + publicacion[0]);
                consultarPublicacionesController.checkPost(publicacion[0]);
            }
        }
         */

        iconX.addMouseListener(onDenny);

        /*
         * new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(panel, "Publicación denegada: " + publicacion[0]);
                consultarPublicacionesController.denyPost(publicacion[0]);
            }
        }
         */

        centerPanel.add(iconCheck, BorderLayout.WEST);
        centerPanel.add(iconX, BorderLayout.EAST);

        rightPanel.add(centerPanel, BorderLayout.CENTER);

        return rightPanel;
    }


    static private ImageIcon loadIcon(String path) {
        URL url = HeaderFactory.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }
}