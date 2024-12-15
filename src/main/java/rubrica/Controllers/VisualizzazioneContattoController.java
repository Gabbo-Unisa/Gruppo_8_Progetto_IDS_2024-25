package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import rubrica.Models.Contatto;
import rubrica.Utils.ContattoManager;
import rubrica.Utils.RubricaManager;

import java.util.List;


public class VisualizzazioneContattoController {
    @FXML
    public Button modificaButton;

    @FXML
    public Button eliminaButton;

    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

    List<String> telefoni;
    List<String> email;

    public void initialize(/*ActionEvent getMouseEvent*/) {
        Contatto c = ContattoManager.getContatto();
        telefoni = ContattoManager.getContatto().getNumeriTelefono();
        email = ContattoManager.getContatto().getEmail();

        lblNome.setText(c.getNome());
        lblCognome.setText(c.getCognome());

        if(telefoni.size() == 0) {              //Caso in cui la lista è vuota
            lblTelefono1.setText("Nessun numero di telefono inserito.");
            lblTelefono2.setText("");
            lblTelefono3.setText("");
        } else if(telefoni.size() == 1){        //Caso in cui la lista ha un solo n. di telefono
            lblTelefono1.setText(telefoni.get(0));
            lblTelefono2.setText("");
            lblTelefono3.setText("");
        }else if(telefoni.size() == 2) {        //Caso in cui la lista ha due n. di telefono
            lblTelefono1.setText(telefoni.get(0));
            lblTelefono2.setText(telefoni.get(1));
            lblTelefono3.setText("");
        }else if(telefoni.size() == 3) {        //Caso in cui la lista ha tre n. di telefono
            lblTelefono1.setText(telefoni.get(0));
            lblTelefono2.setText(telefoni.get(1));
            lblTelefono3.setText(telefoni.get(2));
        }

        if(email.size() == 0) {              //Caso in cui la lista è vuota
            lblEmail1.setText("Nessuna email inserita.");
            lblEmail2.setText("");
            lblEmail3.setText("");
        } else if(email.size() == 1){        //Caso in cui la lista ha una sola email
            lblEmail1.setText(email.get(0));
            lblEmail2.setText("");
            lblEmail3.setText("");
        }else if(email.size() == 2) {        //Caso in cui la lista ha due email
            lblEmail1.setText(email.get(0));
            lblEmail2.setText(email.get(1));
            lblEmail3.setText("");
        }else if(email.size() == 3) {        //Caso in cui la lista ha tre email
            lblEmail1.setText(email.get(0));
            lblEmail2.setText(email.get(1));
            lblEmail3.setText(email.get(2));
        }

        ckbPreferiti.setSelected(c.getIsPreferito());
        txaNote.setText(c.getNota());
        lxlData.setText(c.getDataCreazione());
    }

    /* Alert personalizzato per la conferma dell'eliminazione */
    public void onEliminaClickButton(ActionEvent getActionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questo contatto?");

        ButtonType buttonTypeYes = new ButtonType("Sì");
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Se l'utente conferma l'eliminazione
        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                RubricaManager.eliminaContatto();
                SupportControllers.cambioSchermata(eliminaButton, "/rubrica/Views/ElencoContattiView.fxml");
                SupportControllers.showAlert("Contatto eliminato con successo.");
            } else {
                // Se l'utente annulla l'operazione chiudi l'alert
                alert.close();
            }
        });
    }


    @FXML
    public Label lblNome;

    @FXML
    public Label lblCognome;

    @FXML
    public Label lblTelefono1;

    @FXML
    public Label lblTelefono2;

    @FXML
    public Label lblTelefono3;

    @FXML
    public Label lblEmail1;

    @FXML
    public Label lblEmail2;

    @FXML
    public Label lblEmail3;

    @FXML
    public CheckBox ckbPreferiti;

    @FXML
    public TextArea txaNote;

    @FXML
    public Label lxlData;



/*  NAVIGABILITÀ */
    public void onContattiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(contatti, "/rubrica/Views/ElencoContattiView.fxml");
    }

    public void onPreferitiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(preferiti, "/rubrica/Views/ElencoPreferitiView.fxml");
    }

    public void onModificaClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(modificaButton, "/rubrica/Views/ModificaContattiView.fxml");
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
