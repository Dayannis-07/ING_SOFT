package utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HeaderFactory {
    static public JPanel createHeader() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Palette.instance().getWhite());
        topPanel.setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));
        topPanel.setPreferredSize(Size.TOP_PANEL_SIZE); 

        JPanel iconsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconsPanel.setBackground(Palette.instance().getWhite());
        iconsPanel.setPreferredSize(Size.PANEL_ICONOS_SIZE); 

        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        brandPanel.setBackground(Palette.instance().getWhite());
        brandPanel.setPreferredSize(Size.PANEL_ICON_SIZE); 

        addIcons(iconsPanel, brandPanel);
        topPanel.add(iconsPanel, BorderLayout.EAST);
        topPanel.add(brandPanel, BorderLayout.WEST);

        return topPanel;
    }

    static private void addIcons(JPanel iconsPanel, JPanel brandPanel){
        ImageIcon iconCalendarImg = loadIcon("/Assets/calendar_icon.png");
        ImageIcon iconHomepageImg = loadIcon("/Assets/home_icon2.png");
        ImageIcon iconProfileImg = loadIcon("/Assets/profile_icon2.png");
        ImageIcon iconBHImg = loadIcon("/Assets/bh_icon.jpeg");

        if (iconCalendarImg != null) {
            Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
            iconsPanel.add(iconCalendar);
        }

        if (iconHomepageImg != null) {
            Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
            iconsPanel.add(iconHomepage);
        }

        if (iconProfileImg != null) {
            Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
            iconsPanel.add(iconProfile);
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

