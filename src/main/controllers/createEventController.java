package main.controllers;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class createEventController {

    public static void submitEvent(JTextField txtEventTitle, JTextField txtPlace, JTextField txtDate, JTextArea txtDescription, JLabel selectedFile, JFrame frame) {
        String eventTitle = txtEventTitle.getText().trim();
        String place = txtPlace.getText().trim();
        String date = txtDate.getText().trim();
        String description = txtDescription.getText().trim();
        String filePath = selectedFile.getText().trim();

        if (eventTitle.isEmpty() || place.isEmpty() || date.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidDate(date)) {
            JOptionPane.showMessageDialog(frame, "La fecha debe tener el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isEventAlreadyExists(eventTitle, date)) {
            JOptionPane.showMessageDialog(frame, "El evento ya existe en esta fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        saveEventToFile(eventTitle, place, date, description, filePath);
        JOptionPane.showMessageDialog(frame, "Evento creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isEventAlreadyExists(String eventTitle, String eventDate) {
        File file = new File("events.txt");
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Título: " + eventTitle + ",") && line.contains("Fecha: " + eventDate + ",")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String fileToString(String p) { 
  
        // initializing the variable to 
        // store the string 
        String contents = ""; 
  
        // Instantiating the FileReader class 
        try (FileReader f = new FileReader(p)) { 
  
            // instantiating the BufferedReader class 
            BufferedReader br = new BufferedReader(f); 
  
            // to store the current line read by the 
            // readLine () method 
            String current = ""; 
  
            // looping till we find the null char 
            while ((current = br.readLine()) != null) 
  
                // storing the contents in string 
                contents += current + "\n"; 
        } 
  
        // catch block 
        catch (IOException e) { 
  
            // printing the error 
            e.printStackTrace(); 
        } 
  
        // returning the string 
        return contents; 
    }

    private static void saveEventToFile(String eventTitle, String place, String date, String description, String filePath) {
        JSONObject newPost = new JSONObject();
        JSONArray postsArray = new JSONArray(fileToString("posts.json"));

        newPost.put("Title", eventTitle);
        newPost.put("Place", place);
        newPost.put("Date", date);
        newPost.put("Description", description);
        newPost.put("Path", filePath);
        newPost.put("Approved", false);

        postsArray.put(newPost);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("posts.json", false))) {
            bw.write(postsArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}