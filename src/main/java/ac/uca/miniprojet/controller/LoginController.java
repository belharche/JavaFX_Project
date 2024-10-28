package ac.uca.miniprojet.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorDisplayer;

    private static final String DB_URL = "jdbc:sqlite:users.db";

    @FXML
    public void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateCredentials(username, password)) {
            System.out.println("Login successful for user: " + username);

            URL dashboardUrl = getClass().getResource("@../ac/uca/miniprojet/views/dashboard.fxml");
            if(dashboardUrl == null) throw new RuntimeException("Path To Dashboard View Not Found!");

            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(dashboardUrl);

            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);

            stage.setScene(dashboardScene);
            stage.setMaximized(true);
            stage.show();
        } else {
            System.out.println("Invalid username or password.");

            errorDisplayer.setText("Invalid username or password!!");
        }
    }

    private boolean validateCredentials(String usernameOrEmail, String password) {
        String query = "SELECT * FROM Admins WHERE (username = ? OR email = ?) AND password = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usernameOrEmail);
            preparedStatement.setString(2, usernameOrEmail);
            preparedStatement.setString(3, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}