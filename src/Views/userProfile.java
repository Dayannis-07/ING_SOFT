import javax.swing.*;
import javax.swing.table.JTableHeader;

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
    private JPanel panelNotification;
    private JPanel bottomPanel;
    private JPanel notificationsContainer;
    private JScrollPane scrollPane;
    private JLabel userImageProfile;
    private JPanel header;
    private JPanel footer;
    private JPanel space;

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
        ImageIcon iconCalendarImg = new ImageIcon("../Assets/calendar_icon.png");
        ImageIcon iconHomepageImg = new ImageIcon("../Assets/home_icon2.png");
        ImageIcon iconLogOutImg = new ImageIcon("../Assets/logout_icon2.png");
        ImageIcon iconBHImg = new ImageIcon("../Assets/bh_icon.jpeg");

        Image imgCalendar = iconCalendarImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgHomepage = iconHomepageImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgLogOut = iconLogOutImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Image imgBH = iconBHImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconCalendar = new JLabel(new ImageIcon(imgCalendar));
        JLabel iconHomepage = new JLabel(new ImageIcon(imgHomepage));
        JLabel iconLogOut = new JLabel(new ImageIcon(imgLogOut));
        JLabel iconBH = new JLabel(new ImageIcon(imgBH));

        panelIconos.add(iconCalendar);
        panelIconos.add(iconHomepage);
        panelIconos.add(iconLogOut);
        panelIcon.add(iconBH);

        addIconListeners(iconCalendar, iconHomepage, iconLogOut);
    }

    private void addIconListeners(JLabel iconCalendar, JLabel iconHomepage, JLabel iconLogOut) {
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

        iconLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame,
                    "¿Seguro que deseas cerrar sesión?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new logIn();
                }
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
        
        createPanelCenter();
        createPanelBlue();

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
        panelGris.setPreferredSize(Size.GRAY_PANEL_USERPROFILE_SIZE); 
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 20)); 

    }

    private void createCircleImage() {
        ImageIcon userImage = new ImageIcon("../Assets/userProfileImage.jpg");
        Image userImageCircle = userImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        userImageProfile = new JLabel(new ImageIcon(userImageCircle)); 

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

    private void createPanelBlue() {
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


    private void createPanelCenter() {
        panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setPreferredSize(new Dimension(535, 390)); 
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelCenter.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 6)); 
        panelCenter.setBackground(Palette.instance().getWhite());
        panelCenter.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        //panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        createPanelNotification();
        scrollPaneNotification();
        createSpaceScroll();
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST; 

        gbc.gridy = 1;
        panelCenter.add(scrollPane, gbc);
    } 

    private void createHeader(){
        header = new JPanel();
        header.setPreferredSize(new Dimension(500,40));
        header.setBackground(Palette.instance().getOtherLightGray());
        header.setBorder(BorderFactory.createLineBorder(Palette.instance().getOtherLightGray(), 1, true));
        header.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5)); 

        ImageIcon iconViewMoreImg = new ImageIcon("../Assets/viewMore.png");

        Image imgViewMore = iconViewMoreImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        JLabel iconViewMore = new JLabel(new ImageIcon(imgViewMore));

        header.add(iconViewMore, BorderLayout.NORTH);

        addIconListenersHeader(iconViewMore);
    }

    private void addIconListenersHeader(JLabel iconViewMore) {
        iconViewMore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Ver detalles...");
            }
        });
    }


    private void createFooter(){
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(500,40));
        footer.setBackground(Palette.instance().getOtherLightGray());
        footer.setBorder(BorderFactory.createLineBorder(Palette.instance().getOtherLightGray(), 1, true));
        footer.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); 

        ImageIcon iconLikeImg = new ImageIcon("../Assets/like.png");
        ImageIcon iconComentImg = new ImageIcon("../Assets/coment.png");

        Image imgLike = iconLikeImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imgComent = iconComentImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        JLabel iconLike = new JLabel(new ImageIcon(imgLike));
        JLabel iconComent = new JLabel(new ImageIcon(imgComent));

        footer.add(iconLike);
        footer.add(iconComent);

        addIconListenersFooter(iconLike, iconComent);
    }

    private void addIconListenersFooter(JLabel iconLike, JLabel iconComent) {
        iconLike.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "le diste 'Me gusta' a la publicación...");
            }
        });
        iconComent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Abrir ventana de comentario...");
            }
        });
    }

    private JPanel createPanelNotification(){
        panelNotification = new JPanel(new BorderLayout());
        panelNotification.setBackground(Palette.instance().getLightGray());
        panelNotification.setPreferredSize(new Dimension(500, 370)); 
        panelNotification.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelNotification.setBackground(Palette.instance().getWhite());
        panelNotification.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        panelNotification.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        createHeader();
        createFooter();

        panelNotification.add(header, BorderLayout.NORTH);
        panelNotification.add(footer, BorderLayout.SOUTH);

        return panelNotification;
    }

    private JPanel createSpaceScroll(){
        space = new JPanel();
        space.setPreferredSize(new Dimension(500,5));
        space.setBackground(Palette.instance().getWhite());
        space.setBorder(BorderFactory.createLineBorder(Palette.instance().getWhite(), 1, true));

        return space;
    }

    private void scrollPaneNotification(){
        // Panel contenedor de notificaciones con BoxLayout
        notificationsContainer = new JPanel();
        notificationsContainer.setLayout(new BoxLayout(notificationsContainer, BoxLayout.Y_AXIS));
        notificationsContainer.setBackground(Color.BLUE);

        // Agregar 5 instancias de panelNotification
        for (int i = 0; i < 5; i++) {
            JPanel notification = createPanelNotification();
            JPanel spaceBetweenNotification = createSpaceScroll();
            notificationsContainer.add(notification);
            notificationsContainer.add(spaceBetweenNotification);
        }

        // Agregar el contenedor dentro de un JScrollPane
        scrollPane = new JScrollPane(notificationsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(520, 375));
    }

    
    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Palette.instance().getBeige());
        bottomPanel.setPreferredSize(Size.BOTTOM_PANEL_SIZE); 

        ImageIcon iconAddImg = new ImageIcon("../Assets/add_icon.png");

        Image imgAdd = iconAddImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel iconAdd = new JLabel(new ImageIcon(imgAdd));

        bottomPanel.add(iconAdd);

        addIconListenersBottomPanel(iconAdd);
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


    private void assembleFrame() {
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        new userProfile();
    }
}