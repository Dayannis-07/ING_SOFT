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
    private JPanel panelBlue;
    private JPanel panelCenter;
    private JPanel bottomPanel;
    private JLabel userImageProfile;

    public userProfile() {
        createFrame();
        createTopPanel();
        createMainPanel();
        createBottomPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Perfil de usuario");
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
        createCircleImage();
        infoPersonUser();
        createPanelBlue();
        createPanelCenter();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST; 

        gbc.gridy = 1;
        panel.add(userImageProfile, gbc);
        panel.add(panelGris, gbc);

        gbc.gridy = 2; 
        panel.add(panelBlue, gbc);

        panel.add(panelCenter, gbc);
    }

    private void createGrayPanel() {
        panelGris = new JPanel(new GridBagLayout());
        panelGris.setBackground(Palette.instance().getLightGray());
        panelGris.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        panelGris.setPreferredSize(Size.PANEL_GRIS_USERPROFILE_SIZE); 
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 20)); 

    }

    private void createCircleImage() {
        ImageIcon userImage = new ImageIcon("userProfileImage.jpg");
        Image userImageCircle = userImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        userImageProfile = new JLabel(new ImageIcon(userImageCircle)); 

    }

    private void createPanelBlue() {
        panelCenter = new JPanel();
        panelCenter.setBackground(Palette.instance().getLightGray());
        panelCenter.setPreferredSize(new Dimension(535, 390)); 
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelCenter.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20)); 
        panelCenter.setBackground(Palette.instance().getBlue());
        panelCenter.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
    }

    private void notifications(){
        JLabel lblNotification = new JLabel("Notificaciones");
        lblNotification.setFont(new Font("Arial", Font.BOLD, 20));
        lblNotification.setForeground(Palette.instance().getWhite());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelBlue.add(lblNotification, gbc);

    }

    private void activity(){
        JLabel lblActivity = new JLabel("Actividades");
        lblActivity.setFont(new Font("Arial", Font.BOLD, 20));
        lblActivity.setForeground(Palette.instance().getWhite());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelBlue.add(lblActivity, gbc);
    }

    private void estudyGroup(){
        JLabel lblEstudyGroup = new JLabel("Grupos de estudio");
        lblEstudyGroup.setFont(new Font("Arial", Font.BOLD, 20));
        lblEstudyGroup.setForeground(Palette.instance().getWhite());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelBlue.add(lblEstudyGroup, gbc);
    }

    private void workshops(){
        JLabel lblWorkshops = new JLabel("Talleres                            ");
        lblWorkshops.setFont(new Font("Arial", Font.BOLD, 20));
        lblWorkshops.setForeground(Palette.instance().getWhite());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelBlue.add(lblWorkshops, gbc);
    }

    private void club(){
        JLabel lblClub = new JLabel("Club                                     ");
        lblClub.setFont(new Font("Arial", Font.BOLD, 20));
        lblClub.setForeground(Palette.instance().getWhite());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelBlue.add(lblClub, gbc);
    }

    private void information(){
        JLabel lblInformation = new JLabel("Información");
        lblInformation.setFont(new Font("Arial", Font.BOLD, 20));
        lblInformation.setForeground(Palette.instance().getWhite());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelBlue.add(lblInformation, gbc);
    }

    private void infoPersonUser() {
        JLabel lblInfoPerson = new JLabel("Información personal del usuario");
        lblInfoPerson.setFont(new Font("Arial", Font.BOLD, 20));
        lblInfoPerson.setForeground(Palette.instance().getGray());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 20, 0);

        panelGris.add(lblInfoPerson, gbc);
    }

    private void createPanelCenter() {
        panelBlue = new JPanel();
        panelBlue.setBackground(Palette.instance().getLightGray());
        panelBlue.setPreferredSize(new Dimension(200, 390)); 
        panelBlue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlue.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20)); 
        panelBlue.setBackground(Palette.instance().getBlue());
        panelBlue.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));

        notifications();
        activity();
        estudyGroup();
        workshops();
        club();
        information();
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