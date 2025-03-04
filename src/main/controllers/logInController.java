package main.controllers;

import main.models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class logInController {
    private static final String USERS_FILE = "src/main/persistence/users.json"; // Archivo JSON para usuarios

    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            String jsonContent = fileToString(USERS_FILE);
            JSONArray usersArray = new JSONArray(jsonContent);

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJson = usersArray.getJSONObject(i);
                User user = new User(
                    userJson.getString("name"),
                    userJson.getString("lastName"),
                    userJson.getString("email"),
                    userJson.getString("password"),
                    userJson.getString("userType"),
                    userJson.getBoolean("isAdmin")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public String validateCredentials(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return "Por favor, complete todos los campos.";
        }

        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    return "Inicio de sesión exitoso. Bienvenido, " + user.getName() + " " + user.getLastName() + ".";
                } else {
                    return "Contraseña incorrecta.";
                }
            }
        }
        return "Usuario no registrado.";
    }

    // Método para verificar si el usuario es administrador
    public boolean isAdmin(String email) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getIsAdmin()) {
                return true;
            }
        }
        return false;
    }

    private String fileToString(String filePath) {
        StringBuilder contents = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String current;
            while ((current = br.readLine()) != null) {
                contents.append(current).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents.toString();
    }
}