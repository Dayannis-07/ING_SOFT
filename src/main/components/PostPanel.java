package main.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.models.Post;
import main.utils.Palette;
import main.utils.Size;

public class PostPanel extends JPanel {
    public PostPanel(Post post) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);

        JPanel leftPanel = createLeftPanel(post);

        add(leftPanel, BorderLayout.WEST);
    }
    private JPanel createLeftPanel(Post post) {
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
}
