package main.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import main.models.User;

import java.io.*;

public class signUpController {
    private static final String USERS_FILE = "src/main/persistence/users.json"; // Archivo JSON para usuarios

    public String registerUser(String email, String password, String confirmPassword, String userType, String name,
            String lastName) {
        // Validar campos vacíos
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userType.isEmpty() || name.isEmpty()
                || lastName.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }

        if (!validateEmail(email)) {
            return "El correo electrónico no es válido.";
        }

        if (!validatePassword(password)) {
            return "La contraseña debe tener: al menos 8 caracteres, una mayúscula, un número y un carácter especial.";
        }

        if (!password.equals(confirmPassword)) {
            return "Las contraseñas no coinciden.";
        }

        if (userExists(email)) {
            return "Este correo ya está registrado.";
        }

        // Determinar si el usuario es administrador
        boolean isAdmin = userType.equals("Personal Administrativo");

        // Crear una instancia del modelo User
        User newUser = new User(name, lastName, email, password, userType, isAdmin);

        if (saveUser(newUser)) {
            return "Registro exitoso.";
        } else {
            return "Error al guardar los datos.";
        }
    }

    private static boolean validateEmail(String email) {
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailPattern);
    }

    private static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
        return password.matches(passwordPattern);
    }

    private static boolean userExists(String email) {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return false; // Si el archivo no existe, el usuario no está registrado
        }

        try {
            String jsonContent = fileToString(USERS_FILE);
            JSONArray usersArray = new JSONArray(jsonContent);

            // Buscar el usuario por su correo electrónico
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                String registeredEmail = user.getString("email");

                if (registeredEmail.equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para guardar el usuario en el archivo JSON
    private static boolean saveUser(User user) {
        JSONObject newUser = new JSONObject();
        newUser.put("name", user.getName());
        newUser.put("lastName", user.getLastName());
        newUser.put("email", user.getEmail());
        newUser.put("password", user.getPassword());
        newUser.put("userType", user.getUserType());
        newUser.put("isAdmin", user.getIsAdmin());

        JSONArray usersArray;

        // Leer el archivo JSON existente o crear uno nuevo si no existe
        File file = new File(USERS_FILE);
        if (file.exists()) {
            String jsonContent = fileToString(USERS_FILE);
            usersArray = new JSONArray(jsonContent);
        } else {
            usersArray = new JSONArray();
        }

        // Agregar el nuevo usuario al array
        usersArray.put(newUser);

        // Guardar el array actualizado en el archivo JSON
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, false))) {
            bw.write(usersArray.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String fileToString(String filePath) {
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