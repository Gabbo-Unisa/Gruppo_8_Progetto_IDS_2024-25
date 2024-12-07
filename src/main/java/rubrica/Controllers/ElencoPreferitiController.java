package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ElencoPreferitiController {
    @FXML
    public TextField barraDiRicercaPreferiti;

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
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoPreferitiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) preferiti.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo per passare dalla vis.Preferiti alla vis.Contatti
    // cliccando sulla barra di ricerca di vis.Preferiti

    /*public void onBarraDiRicercaMouseReleased(ActionEvent getActionEvent) {
        barraDiRicercaPreferiti.setFocusTraversable(false);
        try {
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoContattiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) barraDiRicercaPreferiti.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
