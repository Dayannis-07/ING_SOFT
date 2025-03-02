
import javax.swing.*;

import utils.Palette;
import utils.Size;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FooterFactory {

    JFrame frame;
        // Class<JFrame> CalendarClass;
        // Class<JFrame> EventsClass;
        // Class<JFrame> UserProfileClass;
    
        FooterFactory(JFrame _frame) {
            frame = _frame;
        //     CalendarClass = Calendar;
        //     EventsClass = Events;
        //     UserProfileClass = UserProfile;
        }
        
        public JPanel createBottomPanel() {
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(Palette.instance().getBeige());
            bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 
    
            ImageIcon iconAddImg = new ImageIcon("../Assets/add_icon.png");
    
            Image imgAdd = iconAddImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    
            JLabel iconAdd = new JLabel(new ImageIcon(imgAdd));
    
            bottomPanel.add(iconAdd);
    
            addIconListenersBottomPanel(iconAdd);
                                
            return bottomPanel;
        }
                                
        private void addIconListenersBottomPanel(JLabel iconAdd) {
            iconAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //JOptionPane.showMessageDialog(frame, "Agregar evento...");
                    frame.dispose();
                    new createEvent();
                }
            });
        }
}
