package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import rubrica.Models.Contatto;
import rubrica.Utils.RubricaManager;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public void onContattiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(contatti, "/rubrica/Views/ElencoContattiView.fxml");
    }

    public void onPreferitiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(preferiti, "/rubrica/Views/ElencoPreferitiView.fxml");
    }

    public void onAnnullaClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(annulla, "/rubrica/Views/ElencoContattiView.fxml");
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

    /*@FXML
    public ImageView immagine;*/

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

    Contatto c;

        public boolean onSalvaClickButton(ActionEvent getActionEvent) {
            try {
                c = new Contatto(txfNome.getText(), txfCognome.getText(), listaTelefoni(), listaEmail(),
                        txaNote.getText(), ckbPreferiti.isSelected()/*, immagine*/);

                return RubricaManager.getRubrica().aggiungiContatto(c);
            } catch (Exception getE) {
                throw new RuntimeException(getE);
            } finally {
                SupportControllers.cambioSchermata(salvaButton, "/rubrica/Views/ElencoContattiView.fxml");
                SupportControllers.showAlert("Contatto salvato con successo.");
            }

        }





    /*L'invio non viene inserito nei TextField, quindi vanno sostituiti con dei TextArea, alias se avanza tempo lo faccio 😂*/

    /*public void onInvioTyped(KeyEvent KeyEvent) {
        txfNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > oldValue.length()) {
                // L'ultimo carattere digitato
                char lastChar = newValue.charAt(newValue.length() - 1);

                System.out.println("Ultimo carattere digitato: " + lastChar);

                // Controlla se è '\n' o qualsiasi altra condizione
                if (lastChar == '\n') {
                    System.out.println("Hai premuto Invio!");
                    onSalvaClickButton(new ActionEvent());
                }
            }
        });

    }*/




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
