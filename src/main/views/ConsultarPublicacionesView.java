package main.views;

import javax.swing.*;
import main.components.FooterFactory;
import main.components.HeaderFactory;
import main.components.PostPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import main.controllers.consultarPublicacionesController;
import main.models.Post;
import main.utils.Size;

public class ConsultarPublicacionesView {
    private JFrame frame;
    private JPanel panel;
    private consultarPublicacionesController controller;

    public ConsultarPublicacionesView() {
        controller = new consultarPublicacionesController(true);
        initializeFrame();
        initializeHeaderAndFooter();
        initializeMainPanel();
        addTitleToPanel();
        createPostsWithFilter("", "");

        // Hacer visible el frame
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Consultar Publicaciones");
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

    private void initializeMainPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void addTitleToPanel() {
        JPanel titulo = new JPanel(new BorderLayout());
        titulo.setBackground(Color.WHITE);

        JLabel lblConsultarPosts = new JLabel("Consultar posts");
        lblConsultarPosts.setFont(new Font("Arial", Font.BOLD, 20));

        JButton searchButton = createSearchButton();
        searchButton.setPreferredSize(new Dimension(30, 30)); // Ajustar tamaño del botón
        titulo.add(searchButton, BorderLayout.EAST);

        titulo.add(lblConsultarPosts, BorderLayout.WEST);
        panel.add(titulo, BorderLayout.NORTH);
    }

    private JButton createSearchButton() {
        ImageIcon searchIcon = loadIcon("/assets/search_icon.png");
        Image searchImage = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(searchImage);

        JButton searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(30, 30));
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPublicacionesView buscarView = new BuscarPublicacionesView(frame);
                buscarView.addBuscarListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fecha = buscarView.getFecha();
                        String titulo = buscarView.getTitulo();
                        // Aquí puedes añadir la lógica para filtrar las publicaciones
                        panel.removeAll();
                        addTitleToPanel();
                        createPostsWithFilter(fecha, titulo);
                        panel.revalidate();
                        frame.repaint();
                        buscarView.dispose();
                    }
                });
                buscarView.setVisible(true);
            }
        });
        return searchButton;
    }

    private void createPostsWithFilter(String date, String title) {
        ArrayList<Post> posts;

        System.out.println(date);

        if ((date.equals("dd/mm/yyyy") || date.equals("")) && title.equals("")) {
            posts = controller.getPosts();
            System.out.println("Filtrado por ninguno");
        } else if (title.equals("")) {
            posts = controller.filterByDate(date);
            System.out.println("Filtrado por date");
        } else if (date.equals("dd/mm/yyyy")) {
            posts = controller.filterByTitle(title);
            System.out.println("Filtrado por title");
        } else {
            posts = controller.filterByBoth(date, title);
            System.out.println("Filtrado por ambos");
        }

        JPanel publicationsPane = new JPanel();
        publicationsPane.setLayout(new BoxLayout(publicationsPane, BoxLayout.Y_AXIS));

        for (int i = posts.size()-1; i >= 0; i--) {
            final int index = i;
            JPanel pubPanel = new PostPanel(posts.get(i), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new PostView(posts.get(index).getId());
            }
        });
            publicationsPane.add(pubPanel);
        }

        panel.add(new JScrollPane(publicationsPane));
    }

    static private ImageIcon loadIcon(String path) {
        URL url = HeaderFactory.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        new ConsultarPublicacionesView();
    }
}