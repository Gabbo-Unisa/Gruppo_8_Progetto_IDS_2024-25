package rubrica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rubrica.Controllers.SupportControllers;

import java.io.IOException;

public class ApplicazioneProgetto extends Application {
    public SupportControllers supporter= new SupportControllers();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicazioneProgetto.class.getResource("/rubrica/Views/ElencoContattiView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rubrica Telefonica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}