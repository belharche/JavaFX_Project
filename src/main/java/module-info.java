module ac.uca.miniprojet {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens ac.uca.miniprojet to javafx.fxml;
    opens ac.uca.miniprojet.controller to javafx.fxml;
    exports ac.uca.miniprojet;
}