package main.components;
import javax.swing.*;
import main.utils.*;

public class FooterFactory {
    
    static public JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Palette.instance().getBeige());
        bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 

        return bottomPanel;
    }
}
