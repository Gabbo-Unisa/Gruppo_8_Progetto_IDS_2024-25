package org.example.rubricatelefonicaids_gruppo8.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ElencoContattiController /*implements initializable*/ {
    @FXML
    public Button TastoPiù;

    @FXML
    public TextField barraDiRicerca;

    @FXML
    public void onPlusClick(javafx.event.ActionEvent event) {
        barraDiRicerca.setText("Persona aggiunta");
    }

    /*@FXML
    public void onContatti*/



}
