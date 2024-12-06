package rubrica.Controllers;

import ezvcard.VCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import rubrica.Models.Contatto;

import java.io.File;

public class CreaContattoController {
    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

    @FXML
    public Button annulla;

    public void onContattiClickButton(ActionEvent getActionEvent) {
        try {
            // Carica il file FXML della nuova scena
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoContattiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            // Ottieni lo Stage corrente e cambia la scena
            Stage stage = (Stage) contatti.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPreferitiClickButton(ActionEvent getActionEvent) {
        try {
            // Carica il file FXML della nuova scena
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoPreferitiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            // Ottieni lo Stage corrente e cambia la scena
            Stage stage = (Stage) preferiti.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAnnullaClickButton(ActionEvent getActionEvent) {
        try {
            // Carica il file FXML della nuova scena
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoContattiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            // Ottieni lo Stage corrente e cambia la scena
            Stage stage = (Stage) annulla.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void addImageButton(ActionEvent getActionEvent) {
        VCard v = new VCard();

        File photoFile = new File("C:/Users/gabbi/Desktop/Ingegneria del software/1Progetto/Projetto/Rubrica_telefonica_Gruppo_8/Immagine 2024-12-05 233219.jpg");
        v.addPhoto(photoFile);

    }*/
}
