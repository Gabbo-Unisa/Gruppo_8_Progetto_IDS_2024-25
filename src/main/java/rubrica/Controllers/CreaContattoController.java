package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import javax.swing.*;

public class CreaContattoController {
    @FXML
    public Button annulla;

    public void onAnnullaClickButton(ActionEvent getActionEvent) {
        try {
            Parent nuovaScenaRoot = FXMLLoader.load(getClass().getResource("/rubrica/Views/ElencoContattiView.fxml"));
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) annulla.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
