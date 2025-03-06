package main.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import main.utils.*;
import main.views.*;

public class HeaderSignUp extends JPanel {
    private JFrame frame;

    public HeaderSignUp(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(Palette.instance().getWhite());
        setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));
        setPreferredSize(Size.TOP_PANEL_SIZE);
 
        JPanel iconsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconsPanel.setBackground(Palette.instance().getWhite());
        iconsPanel.setPreferredSize(Size.PANEL_ICONOS_SIZE);
 
        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        brandPanel.setBackground(Palette.instance().getWhite());
        brandPanel.setPreferredSize(Size.PANEL_ICON_SIZE);
 
        addIcons(iconsPanel, brandPanel);
        add(iconsPanel, BorderLayout.EAST);
        add(brandPanel, BorderLayout.WEST);
    }
 
    private void addIcons(JPanel iconsPanel, JPanel brandPanel){
        ImageIcon iconProfileImg = loadIcon("/assets/profile_icon2.png");
        ImageIcon iconBHImg = loadIcon("/assets/bh_icon.jpeg");
 
        if (iconProfileImg != null) {
            Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
            iconsPanel.add(iconProfile);
 
            iconProfile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    frame.dispose();
                    new LogInView();
                }
            });
        }
 
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