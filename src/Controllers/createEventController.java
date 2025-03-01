package Controllers;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

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

    private static void saveEventToFile(String eventTitle, String place, String date, String description, String filePath) {
        JSONObject event = new JSONObject();

        event.put("title", eventTitle);
        event.put("place", place);
        event.put("date", date);
        event.put("description", description);
        event.put("file", filePath);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("events.json", true))) {
            bw.write(event.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}