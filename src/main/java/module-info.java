module org.example.rubrica {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires ez.vcard;
    requires jdk.compiler;

    exports rubrica.Models;
    opens rubrica.Models to javafx.fxml;
    opens rubrica.Controllers to javafx.fxml;
    exports rubrica;
    opens rubrica to javafx.fxml;
    opens rubrica.Utils to javafx.fxml;
}