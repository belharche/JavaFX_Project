package org.example.miniprojetjavafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/miniprojetjavafx/views/AdminView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Admin Panel");
            stage.setScene(scene);
            stage.sizeToScene();  // Automatically sizes the stage to the scene's content
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Ideally, log this error or display an alert dialog
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
