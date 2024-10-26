module org.example.miniprojetjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.miniprojetjavafx.Controller;
    opens org.example.miniprojetjavafx.Controller to javafx.fxml;
    exports org.example.miniprojetjavafx.main;
    opens org.example.miniprojetjavafx.main to javafx.fxml;
}