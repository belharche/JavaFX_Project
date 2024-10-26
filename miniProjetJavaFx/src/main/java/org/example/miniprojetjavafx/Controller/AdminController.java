package org.example.miniprojetjavafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;

public class AdminController {
    @FXML
    private BorderPane changeableBorderPane; // The center part of your BorderPane

    // Method to load a new view into the center pane
    private void loadView(String fxmlFile){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            changeableBorderPane.setCenter(loader.load());
        } catch (IOException e) {
            System.err.println("Failed to load FXML file: " + fxmlFile);
            e.printStackTrace();
        }

    }

    // Button click handlers
    @FXML
    private void onProfileButtonClick() {
        loadView("/org/example/miniprojetjavafx/views/ProfileView.fxml");
    }

    @FXML
    private void onAddButtonClick() {
        loadView("/org/example/miniprojetjavafx/views/AddView.fxml");
    }

    @FXML
    private void onStatsButtonClick() {
        loadView("/org/example/miniprojetjavafx/views/StatsView.fxml");
    }

    @FXML
    private void onPatientsButtonClick() {
        loadView("/org/example/miniprojetjavafx/views/PatientsView.fxml");
    }
    @FXML
    private void onLogoutButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.showAndWait().ifPresent(response -> {
            if (response ==  ButtonType.OK) {
                System.exit(0);
            }
        });
        System.exit(0);
    }

}
