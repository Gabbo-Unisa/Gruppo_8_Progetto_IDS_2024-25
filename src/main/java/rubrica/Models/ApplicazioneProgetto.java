package rubrica.Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicazioneProgetto extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicazioneProgetto.class.getResource("/rubrica/Views/VisualizzazioneContattoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rubrica Telefonica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}