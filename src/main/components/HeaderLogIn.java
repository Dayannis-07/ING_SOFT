package main.components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import main.utils.*;

public class HeaderLogIn extends JPanel {

    public HeaderLogIn() {
        setLayout(new BorderLayout());
        setBackground(Palette.instance().getWhite());
        setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));
        setPreferredSize(Size.TOP_PANEL_SIZE);
 
        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        brandPanel.setBackground(Palette.instance().getWhite());
        brandPanel.setPreferredSize(Size.PANEL_ICON_SIZE);

        add(brandPanel, BorderLayout.WEST);
        addIcons(brandPanel);
    }
 
    private void addIcons(JPanel brandPanel){

        ImageIcon iconBHImg = loadIcon("/assets/bh_icon.jpeg");
 
        if (iconBHImg != null) {
            Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconBH = new JLabel(new ImageIcon(imgBH));
            brandPanel.add(iconBH);
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