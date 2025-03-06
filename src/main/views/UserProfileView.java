package main.views;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.utils.Palette;
import main.utils.Size;
import main.components.FooterFactory;
import main.components.HeaderFactory;
import main.models.User;

public class UserProfileView extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel panelGris;
    private JPanel panelBlue;
    private JPanel panelCenter;
    private JPanel panelNotification;
    private JPanel notificationsContainer;
    private JScrollPane scrollPane;
    private JLabel userImageProfile;
    private JPanel header;
    private JPanel footer;
    private JPanel space;
    private String imagePath;
    private List<String> imagePaths = new ArrayList<>();
    private String userEmail;
    private JLabel lblInfoPerson;
    private int numberPublications;

    public UserProfileView() {
        this.userEmail = LogInView.userEmail;

        lblInfoPerson = new JLabel();
        lblInfoPerson.setFont(new Font("Arial", Font.BOLD, 20));
        lblInfoPerson.setForeground(Palette.instance().getGray());

        loadUserInfo();
        createFrame();
        initializeHeaderAndFooter();
        createMainPanel();
        assembleFrame();
    }

    private void createFrame() {
        frame = new JFrame("Perfil del usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Size.FRAME_SIZE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
    }

    private void initializeHeaderAndFooter() { 
        // Añadir el header y footer usando HeaderFactory y FooterFactory 
        JPanel header = new HeaderFactory(frame); 
        JPanel footer = new FooterFactory(frame); 
        frame.add(header, BorderLayout.NORTH); 
        frame.add(footer, BorderLayout.SOUTH); 
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
        ImageIcon userImage = new ImageIcon(getClass().getResource("/assets/userProfileImage.jpg"));
        
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

    private String readJsonFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        br.close();

        return content.toString();
    }

    private void loadUserInfo() {
        try {
            String jsonData = readJsonFile("src/main/persistence/users.json");
            JSONArray usersArray = new JSONArray(jsonData);

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);

                //if (user.getString("email").equals(this.userEmail)) {
                    if (user.getString("email").equals(this.userEmail)) {
                    String info = "<html> Nombre: " + user.getString("name") + "<br/>" +
                                  "Apellido: " + user.getString("lastName") + "<br/>" +
                                  "Tipo: " + user.getString("userType") + "</html>";
                    lblInfoPerson.setText(info);
                    break;
                }
            }
        } catch (IOException e) {
            lblInfoPerson.setText("Error al cargar la información.");
        }
    }

    private void infoPersonUser() {
        /*lblInfoPerson = new JLabel(hola);
        lblInfoPerson.setFont(new Font("Arial", Font.BOLD, 20));
        lblInfoPerson.setForeground(Palette.instance().getGray());*/

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 300);

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

        createPanelNotification(imagePath);
        scrollPanelNotification();
        createSpaceScroll();
        loadImagePaths();
 
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

        ImageIcon iconViewMoreImg = new ImageIcon(getClass().getResource("/assets/viewMore.png"));

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

        ImageIcon iconLikeImg = new ImageIcon(getClass().getResource("/assets/like.png"));
        ImageIcon iconComentImg = new ImageIcon(getClass().getResource("/assets/coment.png"));

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

    private void loadImagePaths() {
        imagePaths.clear();
    
        try {
            String jsonData = readJsonFile("src/main/persistence/posts.json");
            JSONArray postsArray = new JSONArray(jsonData);
            numberPublications = postsArray.length();
    
            // Extraer las rutas de imagen y almacenarlas en imagePaths
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject post = postsArray.getJSONObject(i);
                if (post.has("Path")) {
                    String path = post.getString("Path");
                    File imageFile = new File(path);
    
                    if (imageFile.exists()) {
                        imagePaths.add(path); // Solo agregamos la imagen si el archivo existe
                    } else {
                        System.out.println("Imagen no encontrada: " + path);
                    }
                }
            }
    
            if (imagePaths.isEmpty()) {
                System.out.println("No se encontraron imágenes en el archivo JSON.");
            }
    
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error procesando las rutas de imágenes: " + e.getMessage());
        }
    }
    

    private JPanel createPanelNotification(String imagePath){
        panelNotification = new JPanel(new BorderLayout());
        panelNotification.setBackground(Palette.instance().getLightGray());
        panelNotification.setPreferredSize(new Dimension(500, 370)); 
        panelNotification.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelNotification.setBackground(Palette.instance().getWhite());
        panelNotification.setBorder(BorderFactory.createLineBorder(Palette.instance().getDarkGray(), 1, true));
        panelNotification.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        createHeader();
        createFooter();

        // Cargar imagen específica
        ImageIcon notificationImageIcon = new ImageIcon(imagePath);
        Image notificationImage = notificationImageIcon.getImage().getScaledInstance(470, 280, Image.SCALE_SMOOTH);
        JLabel notificationImageLabel = new JLabel(new ImageIcon(notificationImage));

        // Panel para contener la imagen y centrarla
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Palette.instance().getWhite());
        imagePanel.add(notificationImageLabel);

        // Añadir elementos al panel
        panelNotification.add(header, BorderLayout.NORTH);
        panelNotification.add(imagePanel, BorderLayout.CENTER);
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

    private void scrollPanelNotification() {
        loadImagePaths(); // Cargar imágenes al iniciar
    
        notificationsContainer = new JPanel();
        notificationsContainer.setLayout(new BoxLayout(notificationsContainer, BoxLayout.Y_AXIS));
        notificationsContainer.setBackground(Color.BLUE);
    
        int maxPanels = Math.min(imagePaths.size(), numberPublications); //cantidad de imágenes disponibles
        for (int i = 0; i < maxPanels; i++) {
            JPanel notification = createPanelNotification(imagePaths.get(i));
            JPanel spaceBetweenNotification = createSpaceScroll();
            notificationsContainer.add(notification);
            notificationsContainer.add(spaceBetweenNotification);
        }
    
        scrollPane = new JScrollPane(notificationsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(520, 375));
    }

    private void assembleFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new UserProfileView();
    }
}