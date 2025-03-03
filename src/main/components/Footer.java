package main.components;

import javax.swing.JPanel;

import main.utils.Palette;
import main.utils.Size;

public class Footer extends JPanel {
    public Footer() {
        setBackground(Palette.instance().getBeige());
        setPreferredSize(Size.BOTTOM_PANEL_SIZE); 
    }
}