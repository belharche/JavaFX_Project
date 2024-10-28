package ac.uca.miniprojet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Creation of The Database If Not Exists
        DataBase.initializeDataBase();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/login.fxml")));

        Scene loginScene = new Scene(root);
        loginScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setScene(loginScene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}