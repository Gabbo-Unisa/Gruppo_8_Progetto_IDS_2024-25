package rubrica.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import rubrica.Models.Contatto;
import rubrica.Models.FileManager;
import rubrica.Utils.RubricaManager;

import java.io.File;
import java.util.List;

public class ElencoPreferitiController {
    @FXML
    public Button tastoPiu;

    @FXML
    public TextField barraDiRicercaPreferiti;

    @FXML
    public Button contatti;

    @FXML
    public Button preferiti;

    @FXML
    public TableView<Contatto> tabella;

    @FXML
    public TableColumn<Contatto, String> nomeContatto;

    @FXML
    public MenuItem importaRubrica;

    @FXML
    public MenuItem esportaRubrica;


    private ObservableList<Contatto> contattiPreferitiList;

    @FXML
    public void initialize() {
        showContacts();
    }

    private void showContacts() {
        //Inizializza la lista osservabile
        contattiPreferitiList = FXCollections.observableArrayList(RubricaManager.getRubrica().getContattiPreferiti());

        //Collega la lista alla tabella
        tabella.setItems(contattiPreferitiList);

        //Mostra sulla colonna della tabella il nome e/o cognome del contatto
        nomeContatto.setCellValueFactory(cellData -> {
            Contatto contatto = cellData.getValue();
            String nome = contatto.getNome();
            String cognome = contatto.getCognome();

            if (!nome.isEmpty() && !cognome.isEmpty()) {
                return new SimpleStringProperty(nome + " " + cognome);
            } else if (!nome.isEmpty()) {
                return new SimpleStringProperty(nome);
            } else {
                return new SimpleStringProperty(cognome);
            }
        });
    }

    private void showAlert(String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }


    public void onImportaRubricaClickMouse(ActionEvent getActionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona rubrica da importare");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("VCF files (*.vcf)", "*.vcf"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/Backup"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {         //Se l'utente ha selezionato un file
            //Importa la rubrica dal file selezionato
            FileManager fileManager = new FileManager(RubricaManager.getRubrica());
            if(fileManager.importaRubrica(selectedFile.getPath())) {    //Se l'importaRubrica va a buon fine
                //Aggiorna la tabella dei contatti
                showContacts();

                //Mostra un messaggio di conferma
                showAlert("Rubrica importata con successo.");
            }
            else {      //Se l'importaRubrica non va a buon fine
                //Mostra un messaggio di errore
                showAlert("Eccezione durante l'esecuzione della importaRubrica.");
            }
        }
        else {          //Se l'utente non ha selezionato un file
            //Mostra un messaggio di errore
            showAlert("Nessun file selezionato.");
        }
    }

    public void onEsportaRubricaClickMouse(ActionEvent getActionEvent) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Seleziona directory in cui esportare la rubrica");
        dirChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        File selectedDirectory = dirChooser.showDialog(null);
        if (selectedDirectory != null) {         //Se l'utente ha selezionato una directory
            // Esporta la rubrica nella directory selezionata
            FileManager fileManager = new FileManager(RubricaManager.getRubrica());
            if (fileManager.esportaRubrica(selectedDirectory.getPath())) {       //Se l'esportaRubrica va a buon fine
                //Mostra un messaggio di conferma
                showAlert("Rubrica esportata con successo.");
            } else {      //Se l'esportaRubrica non va a buon fine
                // Mostra un messaggio di errore
                showAlert("Eccezione durante l'esecuzione della esportaRubrica.");
            }
        }
        else {      //Se l'utente non ha selezionato una directory
            //Mostra un messaggio di errore
            showAlert("Nessuna directory selezionata.");
        }
    }

    public void onRicercaTyped(KeyEvent getKeyEvent) {
        String query = barraDiRicercaPreferiti.getText();
        if(query.isEmpty()) {
            showContacts();
        } else {
            contattiPreferitiList.clear();
            List<Contatto> cRicercati = RubricaManager.getRubrica().ricercaContatti(query);
            for(Contatto c : cRicercati) {
                if(c.getIsPreferito())
                    contattiPreferitiList.add(c);
            }
        }
    }


    public void onContattiClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(contatti, "/rubrica/Views/ElencoContattiView.fxml");
    }


    public void onPlusClickButton(ActionEvent getActionEvent) {
        SupportControllers.cambioSchermata(preferiti, "/rubrica/Views/CreaContattoView.fxml");
    }


    public void onContattoSelezionato(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Contatto contattoSelezionato = tabella.getSelectionModel().getSelectedItem();
            SupportControllers.cambioSchermata(tabella, "/rubrica/Views/VisualizzazioneContattoView.fxml");
        }
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
