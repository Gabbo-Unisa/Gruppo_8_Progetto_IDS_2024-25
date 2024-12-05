module org.example.rubricatelefonicaids_gruppo8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires ez.vcard;
    requires jdk.compiler;

    opens org.example.rubricatelefonicaids_gruppo8 to javafx.fxml;
    exports org.example.rubricatelefonicaids_gruppo8.Models;
    opens org.example.rubricatelefonicaids_gruppo8.Models to javafx.fxml;
}