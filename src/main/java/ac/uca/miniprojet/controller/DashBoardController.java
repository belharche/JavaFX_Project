package ac.uca.miniprojet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {
    public void loadDashboard(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ac/uca/miniprojet/views/dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.show();
    }



}
