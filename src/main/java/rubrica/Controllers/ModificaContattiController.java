package rubrica.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

public class ModificaContattiController {
    @FXML
    public Button annullaButton;

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
