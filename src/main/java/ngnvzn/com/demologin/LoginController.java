package ngnvzn.com.demologin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class LoginController
{

    // MySQL-Datenbankverbindungsparameter
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_uebung";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "macintosh";

    // FXML-gebundene UI-Elemente
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    // Methode, die beim Klick auf den Login-Button ausgeführt wird
    @FXML
    protected void onLoginButtonClick() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (checkLogin(email, password)) {
            messageLabel.setText("Eingeloggt");
        } else {
            messageLabel.setText("Falsches Passwort oder Email, nochmal versuchen");
        }
    }

    // Prüfe Login-Datenbankverbindung
    private boolean checkLogin(String email, String password) {
        String query = "SELECT * FROM benutzer WHERE email = ? AND passwort = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query))
        {

            // Setze die Eingabewerte in die SQL-Abfrage ein
            stmt.setString(1, email);
            stmt.setString(2, password);

            // Führe die Abfrage aus
            ResultSet rs = stmt.executeQuery();

            // Wenn ein Ergebnis zurückkommt, sind die Anmeldedaten korrekt
            return rs.next();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}