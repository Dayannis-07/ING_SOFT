package Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class logInController {
    private static final String USERS_FILE = "src/Assests/users.txt";

    public static String validateCredentials(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return "Por favor, complete todos los campos.";
        }

        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String registeredEmail = data[0].trim();
                    String registeredPassword = data[1].trim();
                    String userType = data[2].trim();

                    if (registeredEmail.equals(email)) {
                        userFound = true;
                        if (registeredPassword.equals(password)) {
                            return "Inicio de sesión exitoso. Bienvenido, " + userType + ".";
                        } else {
                            return "Contraseña incorrecta.";
                        }
                    }
                }
            }
        } catch (IOException ex) {
            return "Error al leer el archivo de usuarios.";
        }

        return userFound ? "Contraseña incorrecta." : "Usuario no registrado.";
    }
}