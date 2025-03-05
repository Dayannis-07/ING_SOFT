package main.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.models.Post;
import main.utils.Palette;
import main.utils.Size;

public class PostPanel extends JPanel {
    private ActionListener onViewPost;
    public PostPanel(Post post, ActionListener onViewPost) {
        this.onViewPost = onViewPost;
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
        RoundedButton viewButton = new RoundedButton("Ver más", Palette.instance().getDarkGray());

        viewButton.setPreferredSize(Size.BUTTON_SIZE);
        viewButton.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        viewButton.setContentAreaFilled(false);

        viewButton.addActionListener(onViewPost);

        return viewButton;
    }
}
