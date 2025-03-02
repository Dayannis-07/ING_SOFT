package utils;
import javax.swing.*;

public class FooterFactory {
    
    static public JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Palette.instance().getBeige());
        bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 

        return bottomPanel;
    }
}
