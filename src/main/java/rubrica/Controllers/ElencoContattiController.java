package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ElencoContattiController /*implements initializable*/ {
    @FXML
    public Button tastoPiù;

    @FXML
    public TextField barraDiRicerca;

    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

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



    public void onPlusClickButton(ActionEvent getActionEvent) {
        try {
            // Carica il file FXML della nuova scena
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/CreaContattoView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            // Ottieni lo Stage corrente e cambia la scena
            Stage stage = (Stage) tastoPiù.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
