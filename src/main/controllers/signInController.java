package main.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class signInController {
    private static final String USERS_FILE = "src/Assets/users.txt";

    public static String registerUser(String email, String password, String confirmPassword, String userType, String name, String lastName) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userType.isEmpty() || name.isEmpty() || lastName.isEmpty()) {
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

        if (saveUser(email, password, userType, name, lastName)) {
            return "Registro exitoso.";
        } else {
            return "Error al guardar los datos.";
        }
    }

    private static boolean validateEmail(String email) {
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailPattern, email);
    }

    private static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

    private static boolean userExists(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].trim().equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false; 
        }
        return false;
    }

    private static boolean saveUser(String email, String password, String userType, String name, String lastName) {
        try (FileWriter writer = new FileWriter(USERS_FILE, true)) {
            writer.write(email + "," + password + "," + userType + "," + name + "," + lastName + "\n");
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}