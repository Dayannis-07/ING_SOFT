package main.components;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import main.utils.Size;

import java.awt.*;

public class RoundedButton extends JButton {
    Color color;
    public RoundedButton(String label, Color color) {
        super(label);
        this.color = color;

        setPreferredSize(Size.BUTTON_SIZE);
        setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        setContentAreaFilled(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
        }
        super.paintComponent(g);
    }

}
