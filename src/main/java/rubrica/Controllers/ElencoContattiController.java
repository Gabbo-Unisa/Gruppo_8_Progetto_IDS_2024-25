package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
        if(SupportControllers.getDisplayMode() == true){
            SupportControllers.cambioSchermataLight(contatti, "/rubrica/Views/ElencoContattiView.fxml");
        }else{
        SupportControllers.cambioSchermataDark(contatti, "/rubrica/Views/ElencoContattiView.fxml");
        }
    }

    public void onPreferitiClickButton(ActionEvent getActionEvent) {
        if(SupportControllers.getDisplayMode() == true) {
            SupportControllers.cambioSchermataLight(preferiti, "/rubrica/Views/ElencoPreferitiView.fxml");
        }else{
            SupportControllers.cambioSchermataDark(preferiti, "/rubrica/Views/ElencoPreferitiView.fxml");
        }
    }


    public void onPlusClickButton(ActionEvent getActionEvent) {
        if(SupportControllers.getDisplayMode() == true) {
            SupportControllers.cambioSchermataLight(preferiti, "/rubrica/Views/CreaContattoView.fxml");
        }else{
            SupportControllers.cambioSchermataDark(preferiti, "/rubrica/Views/CreaContattoView.fxml");
        }
    }

    public void onVisualizzaTyped(KeyEvent getKeyEvent) {
        if(barraDiRicercaContatti.getText().contains("Visualizza")){
            SupportControllers.cambioSchermataLight(barraDiRicercaContatti, "/rubrica/Views/VisualizzazioneContattoView.fxml");
        }
        System.out.println(SupportControllers.getDisplayMode());
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
        rootSplitPane.getStylesheets().add(getClass().getResource("/CSS/DarkMode.css").toExternalForm());
    }
}
