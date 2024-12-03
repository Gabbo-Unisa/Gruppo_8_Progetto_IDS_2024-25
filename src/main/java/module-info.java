module org.example.rubricatelefonicaids_gruppo8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.rubricatelefonicaids_gruppo8 to javafx.fxml;
    exports org.example.rubricatelefonicaids_gruppo8;
}