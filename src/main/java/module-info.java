module ac.uca.miniprojet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens ac.uca.miniprojet to javafx.fxml;
    exports ac.uca.miniprojet;
}