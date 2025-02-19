import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.Palette;
import utils.Size;

public class userProfile {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel panelIconos;
    private JPanel panelIcon;
    private JPanel panel;
    private JPanel panelGris;
    private JPanel bottomPanel;

    public userProfile() {
        createFrame();
        createTopPanel();
        createMainPanel();
        createBottomPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Registrarse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Size.FRAME_SIZE); 
        frame.setLayout(new BorderLayout());
    }

    private void createTopPanel() {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Palette.instance().getWhite());
        topPanel.setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray(), 1, true));
        topPanel.setPreferredSize(Size.TOP_PANEL_SIZE); 

        panelIconos = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelIconos.setBackground(Palette.instance().getWhite());
        panelIconos.setPreferredSize(Size.PANEL_ICONOS_SIZE); 

        panelIcon = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIcon.setBackground(Palette.instance().getWhite());
        panelIcon.setPreferredSize(Size.PANEL_ICON_SIZE); 

        addIconsToTopPanel();
        topPanel.add(panelIconos, BorderLayout.EAST);
        topPanel.add(panelIcon, BorderLayout.WEST);
    }

    private void addIconsToTopPanel() {
        ImageIcon iconCalendarImg = new ImageIcon("calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("home_icon2.png");
        ImageIcon iconProfileImg = new ImageIcon("profile_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("bh_icon.jpeg");

        Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgProfile = iconProfileImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
        JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
        JLabel iconProfile = new JLabel(new ImageIcon(imgProfile));
        JLabel iconBH = new JLabel(new ImageIcon(imgBH));

        panelIconos.add(iconCalendar);
        panelIconos.add(iconHomepage);
        panelIconos.add(iconProfile);
        panelIcon.add(iconBH);

        addIconListeners(iconCalendar, iconHomepage, iconProfile);
    }

    private void addIconListeners(JLabel iconCalendar, JLabel iconHomepage, JLabel iconProfile) {
        iconCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo al Calendario...");
            }
        });

        iconHomepage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo a la Página Principal...");
            }
        });

        iconProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirigiendo al Perfil...");
            }
        });
    }

    private void createMainPanel() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Palette.instance().getBeige(), 2, true));
        panel.setBackground(Palette.instance().getWhite());
        panel.setPreferredSize(Size.PANEL_SIZE);

        createGrayPanel();
        panel.add(panelGris, new GridBagConstraints());
    }

    private void createGrayPanel() {
        panelGris = new JPanel(new GridBagLayout());
        panelGris.setBackground(Palette.instance().getLightGray());
        panelGris.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        panelGris.setPreferredSize(Size.PANEL_GRIS_REGISTER_SIZE); 

    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Palette.instance().getBeige());
        bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 
    }

    private void assembleFrame() {
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new userProfile();
    }
}