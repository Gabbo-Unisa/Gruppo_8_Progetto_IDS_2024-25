package rubrica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rubrica.Controllers.SupportControllers;
import rubrica.Models.Checker;
import rubrica.Models.Contatto;
import rubrica.Models.FileManager;
import rubrica.Models.Rubrica;
import rubrica.Utils.RubricaManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicazioneProgetto extends Application {

    //Al'avvio dell'esecuzione: Inizializza la rubrica e setta il display mode.
    @Override
    public void init() {
        Checker checker = new Checker();
        Rubrica rubrica = new Rubrica(checker);

//        File backupFile = new File("Backup/rubrica.vcf");
//        if(backupFile.exists()) {
//            FileManager fileManager = new FileManager(rubrica);
//            fileManager.importaRubrica(backupFile.getPath());   //Importa la rubrica da file "Backup/rubrica.vcf".
//        }

        rubrica.aggiungiContatto(new Contatto("Mario", "", new ArrayList<>(), new ArrayList<>(), "Nota1", true));
        rubrica.aggiungiContatto(new Contatto("", "Verdi", new ArrayList<>(), new ArrayList<>(), "Nota2", false));
        rubrica.aggiungiContatto(new Contatto("Andrea", "Bianchi", new ArrayList<>(), new ArrayList<>(), "Nota3", true));

        RubricaManager.inizializza(rubrica);

        SupportControllers.inizializzaDisplayMode(false);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicazioneProgetto.class.getResource("/rubrica/Views/ElencoContattiView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rubrica Telefonica");
        stage.setScene(scene);
        stage.show();
    }

    //Al termine dell'esecuzione: Salva la rubrica su file.
    @Override
    public void stop() {
        FileManager fileManager = new FileManager(RubricaManager.getRubrica());
        fileManager.esportaRubrica("Backup");   //Esporta la rubrica nella directory "Backup".
    }

    public static void main(String[] args) {
        launch();
    }
}