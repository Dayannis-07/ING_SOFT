import javax.swing.*;

import utils.FooterFactory;
import utils.HeaderFactory;
import utils.Palette;
import utils.Size;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;

public class verificarPublicaciones extends JFrame{

    JPanel panel;

    public verificarPublicaciones() {
        // set up frame options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Size.FRAME_SIZE); 
        setLayout(new BorderLayout());

        // build sections
        JPanel header = HeaderFactory.createHeader();
        JPanel footer = FooterFactory.createBottomPanel();

        createMainPanel();

        // add secctions
        add(header, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void createMainPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Verificar Publicaciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBackground(Palette.instance().getWhite());

        panel.add(titulo);


        // Crear publicaciones
        String[][] publicaciones = {
                { "Título Publicación 1", "2023-10-01", "Ubicación 1" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
                { "Título Publicación 3", "2023-10-03", "Ubicación 3" }
        };
    }

    public static void main(String[] args) {
        new verificarPublicaciones();
    }
//         // Panel principal (centro de la ventana)
//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//         panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//         panel.setBackground(Color.WHITE);

//         JPanel titulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
//         titulo.setBackground(Color.WHITE);

//         JLabel lblVerificarPublicaciones = new JLabel("Verificar publicaciones");
//         lblVerificarPublicaciones.setFont(new Font("Arial", Font.BOLD, 20));
//         // lblVerificarPublicaciones.setForeground(new Color(90, 90, 90)); // Gris
//         // oscuro

//         titulo.add(lblVerificarPublicaciones);
//         panel.add(titulo);

//         // Crear publicaciones
//         String[][] publicaciones = {
//                 { "Título Publicación 1", "2023-10-01", "Ubicación 1" },
//                 { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
//                 { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
//                 { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
//                 { "Título Publicación 2", "2023-10-02", "Ubicación 2" },
//                 { "Título Publicación 3", "2023-10-03", "Ubicación 3" }
//         };

//         for (String[] publicacion : publicaciones) {
//             JPanel pubPanel = new JPanel(new BorderLayout());
//             pubPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//             pubPanel.setBorder(BorderFactory.createCompoundBorder(
//                     BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//                     BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
//             ));
//             pubPanel.setBackground(Color.WHITE);
//             // JPanel pubPanel = new JPanel(new BorderLayout());
//             // pubPanel.setBorder(BorderFactory.createCompoundBorder(
//             // BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//             // BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add padding here
//             // ));

//             // Panel izquierdo para detalles de la publicación
//             JPanel leftPanel = new JPanel();
//             leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//             leftPanel.setBackground(Color.WHITE);

//             JLabel titleLabel = new JLabel("Título: " + publicacion[0]);
//             JLabel dateLabel = new JLabel("Fecha: " + publicacion[1]);
//             JLabel locationLabel = new JLabel("Ubicación: " + publicacion[2]);
//             JButton viewButton = new JButton("Ver más"){
//                 @Override
//                 protected void paintComponent(Graphics g) {
//                     if (!isOpaque()) {
//                         Graphics2D g2 = (Graphics2D) g.create();
//                         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                         g2.setColor(Palette.instance().getDarkGray());
//                         g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
//                         g2.dispose();
//                     }
//                     super.paintComponent(g);
//                 }
//             };

//             viewButton.setPreferredSize(Size.BUTTON_SIZE); 
//             viewButton.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
//             viewButton.setContentAreaFilled(false);

//             viewButton.addActionListener(new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     JOptionPane.showMessageDialog(frame, "Mostrando detalles de la publicación: " + publicacion[0]);
//                 }
//             });

//             leftPanel.add(titleLabel);
//             leftPanel.add(dateLabel);
//             leftPanel.add(locationLabel);
//             leftPanel.add(viewButton);

//             // Panel derecho para botones de aceptar y denegar
//             JPanel rightPanel = new JPanel();
//             rightPanel.setLayout(new BorderLayout());
//             rightPanel.setBackground(Color.WHITE);

//             // Panel para central los botones
//             JPanel centerPanel = new JPanel();
//             centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
//             centerPanel.setBackground(Color.WHITE);

//             JButton acceptButton = new CircleButton("✔", Color.GREEN);

//             JButton denyButton = new CircleButton("✖", Color.RED); 

//             acceptButton.addActionListener(new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     JOptionPane.showMessageDialog(frame, "Publicación aceptada: " + publicacion[0]);
//                 }
//             });

//             denyButton.addActionListener(new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     JOptionPane.showMessageDialog(frame, "Publicación denegada: " + publicacion[0]);
//                 }
//             });

//             centerPanel.add(acceptButton);
//             centerPanel.add(denyButton);

//             rightPanel.add(centerPanel, BorderLayout.CENTER);

//             // Agregar paneles izquierdo y derecho al panel de publicación
//             pubPanel.add(leftPanel, BorderLayout.WEST);
//             pubPanel.add(rightPanel, BorderLayout.EAST);

//             // Agregar el panel de publicación al panel principal
//             panel.add(pubPanel);
//         }

//         // Panel inferior (Beige)
//         JPanel bottomPanel = new JPanel();
//         bottomPanel.setBackground(new Color(222, 196, 145)); // Beige
//         bottomPanel.setPreferredSize(new Dimension(800, 45));
//         ImageIcon iconAddImg = new ImageIcon("../Assets/add_icon.png");

//         Image imgAdd = iconAddImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

//         JLabel iconAdd = new JLabel(new ImageIcon(imgAdd));

//         bottomPanel.add(iconAdd);

//         iconAdd.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 //JOptionPane.showMessageDialog(frame, "Agregar evento...");
//                 frame.dispose();
//                 new createEvent();
//             }
//         });

//         // Agregar todo al marco
//         frame.add(topPanel, BorderLayout.NORTH);
//         frame.add(new JScrollPane(panel), BorderLayout.CENTER);
//         frame.add(bottomPanel, BorderLayout.SOUTH);

//         // Mostrar ventana
//         frame.setVisible(true);

//         frame.setLocationRelativeTo(null);
        
//     }
//     public static void main(String[] args) {
//         new verificarPublicaciones();
//     }
// }


// class CircleButton extends JButton{

// 	private boolean mouseOver = false;
// 	private boolean mousePressed = false;
//     private Color buttonColor;

// 	public CircleButton(String text, Color c){
// 		super(text);
// 		setOpaque(false);
// 		setFocusPainted(false);
// 		setBorderPainted(false);

//         buttonColor = c;

// 		MouseAdapter mouseListener = new MouseAdapter(){

// 			@Override
// 			public void mousePressed(MouseEvent me){
// 				if(contains(me.getX(), me.getY())){
// 					mousePressed = true;
// 					repaint();
// 				}
// 			}

// 			@Override
// 			public void mouseReleased(MouseEvent me){
// 				mousePressed = false;
// 				repaint();
// 			}

// 			@Override
// 			public void mouseExited(MouseEvent me){
// 				mouseOver = false;
// 				mousePressed = false;
// 				repaint();
// 			}

// 			@Override
// 			public void mouseMoved(MouseEvent me){
// 				mouseOver = contains(me.getX(), me.getY());
// 				repaint();
// 			}
// 		};

// 		addMouseListener(mouseListener);
// 		addMouseMotionListener(mouseListener);		
// 	}

// 	private int getDiameter(){
// 		int diameter = Math.min(getWidth(), getHeight());
// 		return diameter;
// 	}

// 	@Override
// 	public Dimension getPreferredSize(){
// 		FontMetrics metrics = getGraphics().getFontMetrics(getFont());
// 		int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
// 		return new Dimension(minDiameter, minDiameter);
// 	}

// 	@Override
// 	public boolean contains(int x, int y){
// 		int radius = getDiameter()/2;
// 		return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
// 	}

// 	@Override
// 	public void paintComponent(Graphics g){

// 		int diameter = getDiameter();
// 		int radius = diameter/2;

// 		if(mousePressed){
// 			g.setColor(buttonColor.darker());
// 		}
// 		else{
// 			g.setColor(buttonColor);
// 		}
// 		g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

// 		if(mouseOver){
// 			g.setColor(Color.BLUE);
// 		}
// 		else{
// 			g.setColor(Color.BLACK);
// 		}
// 		//g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);

// 		g.setColor(Color.WHITE);
// 		g.setFont(getFont());
// 		FontMetrics metrics = g.getFontMetrics(getFont());
// 		int stringWidth = metrics.stringWidth(getText());
// 		int stringHeight = metrics.getHeight();
// 		g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
// 	}

}