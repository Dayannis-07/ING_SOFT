package utils;
import java.awt.Color;

public class Palette {
    private static Palette palette;

    private final Color white;
    private final Color gray;
    private final Color lightGray;
    private final Color beige;
    private final Color darkGray;
    private final Color lightGreen;
    private final Color darkGreen;
    private final Color transparentBeige;
    private final Color blue;
    private final Color otherLightGray;

    private Palette() {
        white = new Color(255, 255, 255); // Blanco
        gray = new Color(90,90,90);
        lightGray = new Color(242, 242, 242); // Gris claro
        darkGray = new Color(192, 192, 192); // Gris oscuro
        beige = new Color(222, 196, 145); // Beige
        transparentBeige = new Color(222, 196, 145, 200); // Beige con transparencia
        darkGreen = new Color(29, 140, 31); // Verde oscuro
        lightGreen = new Color(171, 227, 79, 200); // Verde claro con transparencia
        blue = new Color(98, 133, 170, 200); 
        otherLightGray = new Color(166, 166, 166);
    }

    /**
     * Obtiene la instancia única de la paleta.
     *
     * @return La instancia de la paleta.
     */
    public static Palette instance() {
        if (palette == null) {
            palette = new Palette();
        }
        return palette;
    }

    // Getters para los colores

    public Color getWhite() {
        return white;
    }

    public Color getLightGray() {
        return lightGray;
    }

    public Color getBeige() {
        return beige;
    }

    public Color getDarkGray() {
        return darkGray;
    }

    public Color getLightGreen() {
        return lightGreen;
    }

    public Color getDarkGreen() {
        return darkGreen;
    }

    public Color getTransparentBeige() {
        return transparentBeige;
    }

    public Color getGray() {
         return gray;
    }

    public Color getBlue() {
        return blue;
   }

   public Color getOtherLightGray(){
    return otherLightGray;
   }
}