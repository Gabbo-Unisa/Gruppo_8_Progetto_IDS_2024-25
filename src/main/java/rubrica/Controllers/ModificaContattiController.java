package rubrica.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import rubrica.Models.Contatto;
import rubrica.Utils.ContattoManager;
import rubrica.Utils.RubricaManager;

import java.util.ArrayList;
import java.util.List;

public class ModificaContattiController {
    @FXML
    public Button annullaButton;

    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

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

    @FXML
    public Button salvaButton;

    @FXML
    public Label lblData;


    // Prima di poter modificare gli attributi devo popolare i textfield
    List<String> telefoni;
    List<String> email;

    Contatto contattoOld;
    public void initialize() {
        contattoOld = ContattoManager.getContatto();
        telefoni = ContattoManager.getContatto().getNumeriTelefono();
        email = ContattoManager.getContatto().getEmail();

        txfNome.setText(contattoOld.getNome());
        txfCognome.setText(contattoOld.getCognome());

        if(telefoni.size() == 0) {              //Caso in cui la lista è vuota
            txfTelefono1.setText("");
            txfTelefono2.setText("");
            txfTelefono3.setText("");
        } else if(telefoni.size() == 1){        //Caso in cui la lista ha un solo n. di telefono
            txfTelefono1.setText(telefoni.get(0));
            txfTelefono2.setText("");
            txfTelefono3.setText("");
        }else if(telefoni.size() == 2) {        //Caso in cui la lista ha due n. di telefono
            txfTelefono1.setText(telefoni.get(0));
            txfTelefono2.setText(telefoni.get(1));
            txfTelefono3.setText("");
        }else if(telefoni.size() == 3) {        //Caso in cui la lista ha tre n. di telefono
            txfTelefono1.setText(telefoni.get(0));
            txfTelefono2.setText(telefoni.get(1));
            txfTelefono3.setText(telefoni.get(2));
        }

        if(email.size() == 0) {              //Caso in cui la lista è vuota
            txfEmail1.setText("");
            txfEmail2.setText("");
            txfEmail3.setText("");
        } else if(email.size() == 1){        //Caso in cui la lista ha una sola email
            txfEmail1.setText(email.get(0));
            txfEmail2.setText("");
            txfEmail3.setText("");
        }else if(email.size() == 2) {        //Caso in cui la lista ha due email
            txfEmail1.setText(email.get(0));
            txfEmail2.setText(email.get(1));
            txfEmail3.setText("");
        }else if(email.size() == 3) {        //Caso in cui la lista ha tre email
            txfEmail1.setText(email.get(0));
            txfEmail2.setText(email.get(1));
            txfEmail3.setText(email.get(2));
        }

        ckbPreferiti.setSelected(contattoOld.getIsPreferito());
        txaNote.setText(contattoOld.getNota());
        lblData.setText(contattoOld.getDataCreazione());

        //  Mette il focus sulla barra di ricerca
        //  dopo che fxmlLoader ha finito di renderizzare la schermata
        Platform.runLater(txfNome::requestFocus);

    }

    // L'utente effettua le modifiche che ritiene più opportune e clicca sul pulsante "Salva"
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
        Contatto contattoNew = new Contatto(txfNome.getText(), txfCognome.getText(), listaTelefoni(),
                    listaEmail(), contattoOld.getDataCreazione(), txaNote.getText(), ckbPreferiti.isSelected());

            RubricaManager.getRubrica().modificaContatto(contattoOld, contattoNew);

            SupportControllers.cambioSchermata(salvaButton, "/rubrica/Views/ElencoContattiView.fxml");
            if(RubricaManager.getRubrica().getContatti().contains(contattoNew))
                // SE VA TUTTO BENE SI GENERA UN ALERT DI SUCCESSO
                SupportControllers.showAlert("Contatto modificato con successo.");
            else
                // SE IL CONTATTO PER QUALCHE MOTIVO NON VIENE SALVATO SI GENERA UN ALERT DI INSUCCESSO
                SupportControllers.showAlert("Errore durante la modifica del contatto.");
    }

    /* NAVIGABILITÀ */
    public void onContattiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(contatti, "/rubrica/Views/ElencoContattiView.fxml");
    }

    public void onPreferitiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(preferiti, "/rubrica/Views/ElencoPreferitiView.fxml");
    }

    public void onClickAnnulla(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(annullaButton, "/rubrica/Views/VisualizzazioneContattoView.fxml");
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
