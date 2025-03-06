package main.components;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.*;

//import org.w3c.dom.events.MouseEvent;

import main.utils.*;
import main.views.CreatePostView;

public class FooterFactory extends JPanel{
    
    public FooterFactory(JFrame frame) {
        setLayout(new BorderLayout());
        this.setBackground(Palette.instance().getBeige());
        this.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 

        addIcon(frame);
    }

    private void addIcon(JFrame frame){
        ImageIcon addIconImg = loadIcon("/assets/add_icon.png");

        if (addIconImg != null) {
            Image imgAdd = addIconImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconAdd = new JLabel(new ImageIcon(imgAdd));
            add(iconAdd);
            
            iconAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    frame.dispose();
                    new CreatePostView();
                }
            });
        }
    }

    private ImageIcon loadIcon(String path) {
        URL url = HeaderFactory.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }
}