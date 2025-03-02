package utils;
import java.awt.Dimension;

public class Size {
    // Tamaño del frame
    public static final Dimension FRAME_SIZE = new Dimension(800, 700);

    // Tamaño del topPanel
    public static final Dimension TOP_PANEL_SIZE = new Dimension(FRAME_SIZE.width, 50);

    // Tamaño del panelIconos
    public static final Dimension PANEL_ICONOS_SIZE = new Dimension(300, TOP_PANEL_SIZE.height);

    // Tamaño del panelIcon
    public static final Dimension PANEL_ICON_SIZE = new Dimension(100, TOP_PANEL_SIZE.height);

    // Tamaño del panel principal
    public static final Dimension PANEL_SIZE = new Dimension(600, 400);

    // Tamaño del panelGris
    public static final Dimension GRAY_PANEL_SIZE = new Dimension(600, 400);

    // Tamaño del panelGris en register
    public static final Dimension GRAY_PANEL_REGISTER_SIZE = new Dimension(600, 425);

    // Tamaño del panelGris en logIN
    public static final Dimension GRAY_PANEL_LOGIN_SIZE = new Dimension(300, 450);


    // Tamaño del panelGris en userProfile
    public static final Dimension GRAY_PANEL_USERPROFILE_SIZE = new Dimension(650, 120);

    // Tamaño del leftPanel
    public static final Dimension LEFT_PANEL_SIZE = new Dimension(250, GRAY_PANEL_SIZE.height);

    // Tamaño del rightPanel
    public static final Dimension RIGHT_PANEL_SIZE = new Dimension(250, GRAY_PANEL_SIZE.height);

    // Tamaño del bottomPanel
    public static final Dimension BOTTOM_PANEL_SIZE = new Dimension(FRAME_SIZE.width, 45);

    // Tamaño del botón de enviar
    public static final Dimension BUTTON_SIZE = new Dimension(100, 40);
}