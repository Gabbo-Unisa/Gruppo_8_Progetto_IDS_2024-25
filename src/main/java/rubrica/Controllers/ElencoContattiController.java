package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ElencoContattiController {
    @FXML
    public Button tastoPiù;

    @FXML
    public TextField barraDiRicercaContatti;

    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

    public void onContattiClickButton(ActionEvent getActionEvent) {
        try {
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoContattiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

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



    public void onPlusClickButton(ActionEvent getActionEvent) {
        try {
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/CreaContattoView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) tastoPiù.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onVisualizzaTyped(KeyEvent getKeyEvent) {
        if(barraDiRicercaContatti.getText().contains("Visualizza")){
            try {
                Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/VisualizzazioneContattoView.fxml"));
                Scene nuovaScena = new Scene(nuovaScenaRoot);

                Stage stage = (Stage) barraDiRicercaContatti.getScene().getWindow();
                stage.setScene(nuovaScena);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
