

import javax.swing.*;

import utils.Palette;
import utils.Size;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    private void addIcons(JPanel iconsPanel, JPanel brandPanel){
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
    }
}
