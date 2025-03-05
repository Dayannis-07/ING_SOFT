package main.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.components.FooterFactory;
import main.components.HeaderFactory;
import main.controllers.PostController;
import main.models.Post;
import main.utils.Palette;
import main.utils.Size;

public class PostView extends JFrame {
    PostController controller;
    Post currentPost;

    JPanel panel;

    PostView(int id) {
        controller = new PostController(true);
        currentPost = controller.getPost(id);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Size.FRAME_SIZE);
        setBackground(Palette.instance().getWhite());
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setTitle(currentPost.getTitle());

        add(new HeaderFactory(this), BorderLayout.NORTH);
        add(new FooterFactory(this), BorderLayout.SOUTH);

        initializeMainPanel();
        
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initializeMainPanel() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.setBackground(Palette.instance().getWhite());

        displayPost();

        add(panel, BorderLayout.CENTER);
    }

    private void displayPost() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBackground(Palette.instance().getWhite());

        ImageIcon postImgFile = new ImageIcon(currentPost.getPath());
        Image postImg = postImgFile.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        JLabel postImgLbl = new JLabel(new ImageIcon(postImg));

        postPanel.add(postImgLbl);

        JLabel title = new JLabel(currentPost.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBackground(Palette.instance().getWhite());
        postPanel.add(title);

        JLabel dateLabel = new JLabel(currentPost.getDate());
        dateLabel.setFont(new Font("Arial", Font.BOLD, 12));
        dateLabel.setBackground(Palette.instance().getWhite());
        dateLabel.setForeground(Palette.instance().getDarkGray());
        postPanel.add(dateLabel);

        JLabel placeLabel = new JLabel(currentPost.getPlace());
        placeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        placeLabel.setBackground(Palette.instance().getWhite());
        placeLabel.setForeground(Palette.instance().getDarkGray());
        postPanel.add(placeLabel);

        JLabel descriptionLabel = new JLabel(currentPost.getDescription());
        descriptionLabel.setPreferredSize(new Dimension(400, 900));
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel.setBackground(Palette.instance().getWhite());
        postPanel.add(descriptionLabel);

        panel.add(postPanel);
    }
}
