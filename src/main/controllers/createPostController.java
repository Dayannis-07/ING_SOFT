package main.controllers;

import javax.swing.*;


import main.models.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class createPostController extends PostController {

    public createPostController(boolean readOnlyAproved) {
        super(readOnlyAproved);
    }

    public void submitEvent(JTextField txtEventTitle, JTextField txtPlace, JTextField txtDate, JTextArea txtDescription, String selectedFile, JFrame frame) {
        String eventTitle = txtEventTitle.getText().trim();
        String place = txtPlace.getText().trim();
        String date = txtDate.getText().trim();
        String description = txtDescription.getText().trim();
        String filePath = selectedFile;

        if (eventTitle.isEmpty() || place.isEmpty() || date.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidDate(date)) {
            JOptionPane.showMessageDialog(frame, "La fecha debe tener el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Post newPost = new Post(0, filePath, eventTitle, date, place, description, readOnlyAproved);

        addPost(newPost);
        savePosts();
        JOptionPane.showMessageDialog(frame, "Evento creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}