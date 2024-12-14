package rubrica.Controllers;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import rubrica.Models.Contatto;
import rubrica.Utils.RubricaManager;

import java.util.ArrayList;
import java.util.List;

public class CreaContattoController {
    // Navigazione
    @FXML
    public Button salvaButton;

    @FXML
    public Button annulla;

    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

    public void initialize() {
        //  Mette il focus sulla barra di ricerca
        //  dopo che fxmlLoader ha finito di renderizzare la schermata
        Platform.runLater(txfNome::requestFocus);

        //  Disabilita il bottone di salvataggio se i campi nome e cognome sono vuoti
        salvaButton.disableProperty().bind(Bindings.and(
                txfNome.textProperty().isEmpty(), txfCognome.textProperty().isEmpty()
        ));
    }


    // trattamento dati
    @FXML
    public TextField txfNome;

    @FXML
    public TextField txfCognome;

    @FXML
    public TextField txfTelefono1;

    @FXML
    public TextField txfTelefono2;

    @FXML
    public TextField txfTelefono3;

    @FXML
    public TextField txfEmail1;

    @FXML
    public TextField txfEmail2;

    @FXML
    public TextField txfEmail3;

    @FXML
    public TextArea txaNote;

    @FXML
    public CheckBox ckbPreferiti;


    List<String> listaTelefoni;
    private List<String> listaTelefoni() {
        listaTelefoni = new ArrayList<>();

        if(!txfTelefono1.getText().isEmpty())
            listaTelefoni.add(txfTelefono1.getText());
        if(!txfTelefono2.getText().isEmpty())
            listaTelefoni.add(txfTelefono2.getText());
        if(!txfTelefono3.getText().isEmpty())
            listaTelefoni.add(txfTelefono3.getText());

        return listaTelefoni;
    }


    List<String> listaEmail;
    private List<String> listaEmail() {
        listaEmail = new ArrayList<>();

        if(!txfEmail1.getText().isEmpty())
            listaEmail.add(txfEmail1.getText());
        if(!txfEmail2.getText().isEmpty())
            listaEmail.add(txfEmail2.getText());
        if(!txfEmail3.getText().isEmpty())
            listaEmail.add(txfEmail3.getText());

        return listaEmail;
    }


    public void onSalvaClickButton(ActionEvent getActionEvent) {
        Contatto c = new Contatto(txfNome.getText(), txfCognome.getText(), listaTelefoni(), listaEmail(),
                    txaNote.getText(), ckbPreferiti.isSelected());

            boolean aggiunto = RubricaManager.getRubrica().aggiungiContatto(c);

            SupportControllers.cambioSchermata(salvaButton, "/rubrica/Views/ElencoContattiView.fxml");
            if(aggiunto)
                // SE VA TUTTO BENE SI GENERA UN ALERT DI SUCCESSO
                SupportControllers.showAlert("Contatto salvato con successo.");
            else
                // SE IL CONTATTO PER QUALCHE MOTIVO NON VIENE SALVATO SI GENERA UN ALERT DI INSUCCESSO
                SupportControllers.showAlert("Errore durante il salvataggio del contatto.");
    }

    /* NAVIGABILITÀ */
    public void onContattiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(contatti, "/rubrica/Views/ElencoContattiView.fxml");
    }

    public void onPreferitiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(preferiti, "/rubrica/Views/ElencoPreferitiView.fxml");
    }

    public void onAnnullaClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(annulla, "/rubrica/Views/ElencoContattiView.fxml");
    }


    /* DISPLAY MODE ZONE :') */
    @FXML
    public MenuItem lightMode;

    @FXML
    public MenuItem darkMode;

    @FXML
    public SplitPane rootSplitPane;


    public void onLightClickMouse(ActionEvent getActionEvent) {
        // Decido che false sia la modalità scura
        //     mentre true la modalità luminosa
        SupportControllers.setDisplayMode(true);
        rootSplitPane.getStylesheets().clear();
    }

    public void onDarkClickMouse(ActionEvent getActionEvent) {
        // Decido che false sia la modalità scura
        //     mentre true la modalità luminosa
        SupportControllers.setDisplayMode(false);
        rootSplitPane.getStylesheets().add(getClass().getResource("/rubrica/CSS/DarkMode.css").toExternalForm());
    }
}
