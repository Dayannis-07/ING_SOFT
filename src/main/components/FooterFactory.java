package main.components;
import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.*;
import main.utils.*;

public class FooterFactory {
    
    static public JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Palette.instance().getBeige());
        bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 

        addIcon(bottomPanel);

        return bottomPanel;
    }

    static private void addIcon(JPanel bottomPanel){
        ImageIcon addIconImg = loadIcon("/assets/add_Icon.png");

        if (addIconImg != null) {
            Image imgCalendar = addIconImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
            bottomPanel.add(iconCalendar);
        }
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
