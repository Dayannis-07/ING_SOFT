

import javax.swing.*;

import utils.Palette;
import utils.Size;

import java.awt.*;
<<<<<<< HEAD:src/Views/utils/HeaderFactory.java
import java.net.URL;
=======
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
>>>>>>> f092f2fe9e3c17c207208f21723c365b2de3f4b3:src/Views/HeaderFactory.java

public class HeaderFactory {
    JFrame frame;
    // Class<JFrame> CalendarClass;
    // Class<JFrame> EventsClass;
    // Class<JFrame> UserProfileClass;

    HeaderFactory(JFrame _frame) {
        frame = _frame;
    //     CalendarClass = Calendar;
    //     EventsClass = Events;
    //     UserProfileClass = UserProfile;
    }

    public JPanel createHeader() {
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

<<<<<<< HEAD:src/Views/utils/HeaderFactory.java
    static private void addIcons(JPanel iconsPanel, JPanel brandPanel){
        ImageIcon iconCalendarImg = loadIcon("/Assets/calendar_icon.png");
        ImageIcon iconHomepageImg = loadIcon("/Assets/home_icon2.png");
        ImageIcon iconProfileImg = loadIcon("/Assets/profile_icon2.png");
        ImageIcon iconBHImg = loadIcon("/Assets/bh_icon.jpeg");
=======
    private void addIcons(JPanel iconsPanel, JPanel brandPanel){
        ImageIcon iconCalendarImg = new ImageIcon("src/Assets/calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("src/Assets/home_icon2.png");
        ImageIcon iconProfileImg = new ImageIcon("src/Assets/profile_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("src/Assets/bh_icon.jpeg");
>>>>>>> f092f2fe9e3c17c207208f21723c365b2de3f4b3:src/Views/HeaderFactory.java

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

<<<<<<< HEAD:src/Views/utils/HeaderFactory.java
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
=======
        addIconsActions(iconCalendar, iconHomepage, iconProfile);
    }

    private void addIconsActions(JLabel iconCalendar, JLabel iconHomepage, JLabel iconProfile) {
        iconCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo al Calendario...");
                frame.dispose();
                new CalendarApp();
            }
        });

        iconHomepage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo a la Página Principal...");
                frame.dispose();
                new consultarPublicaciones();
            }
        });

        iconProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JOptionPane.showMessageDialog(frame, "Redirigiendo al Perfil...");
                frame.dispose();
                new userProfile();
            }
        });
>>>>>>> f092f2fe9e3c17c207208f21723c365b2de3f4b3:src/Views/HeaderFactory.java
    }
}

