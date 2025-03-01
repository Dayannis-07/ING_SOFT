package utils;

import javax.swing.*;
import java.awt.*;

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
        ImageIcon iconCalendarImg = new ImageIcon("src/Assets/calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("src/Assets/home_icon2.png");
        ImageIcon iconProfileImg = new ImageIcon("src/Assets/profile_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("src/Assets/bh_icon.jpeg");

        Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
        JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
        JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
        JLabel iconBH = new JLabel(new ImageIcon(imgBH));

        iconsPanel.add(iconCalendar);
        iconsPanel.add(iconHomepage);
        iconsPanel.add(iconProfile);
        brandPanel.add(iconBH);

        // addIconListeners(iconCalendar, iconHomepage, iconProfile);
    }
}
