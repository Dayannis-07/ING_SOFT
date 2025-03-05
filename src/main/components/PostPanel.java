package main.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
        JPanel contentPanel = new JPanel();

        contentPanel.setBackground(Palette.instance().getWhite());
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        ImageIcon postImgFile = new ImageIcon(post.getPath());
        Image postImg = postImgFile.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        JLabel postImgLbl = new JLabel(new ImageIcon(postImg));

        JLabel titleLabel = new JLabel("Título: " + post.getTitle());
        JLabel dateLabel = new JLabel("Fecha: " + post.getDate());
        JLabel locationLabel = new JLabel("Ubicación: " + post.getPlace());
        JButton viewButton = createViewButton(post.getTitle());

        contentPanel.add(titleLabel);
        contentPanel.add(dateLabel);
        contentPanel.add(locationLabel);
        contentPanel.add(viewButton);

        if(!post.getPath().equals("Ningún archivo seleccionado")){
            leftPanel.add(Box.createRigidArea(new Dimension(40, 10)), BorderLayout.CENTER);
        }
        leftPanel.add(contentPanel, BorderLayout.EAST);
        leftPanel.add(postImgLbl, BorderLayout.WEST);

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
