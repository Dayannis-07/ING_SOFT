package main.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import main.utils.*;
import main.views.CalendarView;
import main.views.ConsultarPublicacionesView;
import main.views.LogInView;
import main.views.UserProfileView;

public class HeaderFactory extends JPanel {
    private JFrame frame;

    public HeaderFactory(JFrame frame) {
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
        ImageIcon iconCalendarImg = loadIcon("/assets/calendar_icon.png");
        ImageIcon iconHomepageImg = loadIcon("/assets/home_icon2.png");
        ImageIcon iconProfileImg = loadIcon("/assets/profile_icon2.png");
        ImageIcon iconLogOutImg = loadIcon("/assets/logout_icon2.png");
        ImageIcon iconBHImg = loadIcon("/assets/bh_icon.jpeg");

        if (iconCalendarImg != null) {
            Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
            iconsPanel.add(iconCalendar);

            iconCalendar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    frame.dispose();
                    new CalendarView();
                }
            });
        }

        if (iconHomepageImg != null) {
            Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
            iconsPanel.add(iconHomepage);

            iconHomepage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    frame.dispose();
                    new ConsultarPublicacionesView();
                }
            });
        }

        if (iconProfileImg != null) {
            Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
            iconsPanel.add(iconProfile);

            iconProfile.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    frame.dispose();
                    new UserProfileView();
                }
            });
        }

        if (iconLogOutImg != null) {
            Image imgLogOut = iconLogOutImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JLabel iconLogOut = new JLabel(new ImageIcon(imgLogOut));
            iconsPanel.add(iconLogOut);

            iconLogOut.addMouseListener(new MouseAdapter() {
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



