package rubrica.Controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SupportControllers {
    private static boolean displayMode;


    public static void inizializzaDisplayMode(boolean displayMode) {
        SupportControllers.displayMode = displayMode;
    }

    public static boolean getDisplayMode() {
        return SupportControllers.displayMode;
    }

    public static void setDisplayMode(boolean displayMode) {
        SupportControllers.displayMode = displayMode;
    }


    public static void cambioSchermata(Parent elementoFxml, String pathSchermata){
        if(SupportControllers.getDisplayMode() == true){
            SupportControllers.cambioSchermataLight(elementoFxml, pathSchermata);
        }else{
            SupportControllers.cambioSchermataDark(elementoFxml, pathSchermata);
        }
    }

    public static void cambioSchermataLight(Parent elementoFxml, String pathSchermata){
        try {
            Parent nuovaScenaRoot = FXMLLoader.load(SupportControllers.class.getResource(pathSchermata));
            nuovaScenaRoot.getStylesheets().clear();
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) elementoFxml.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambioSchermataDark(Parent elementoFxml, String pathSchermata) {
        try {
            Parent nuovaScenaRoot = FXMLLoader.load(SupportControllers.class.getResource(pathSchermata));
            nuovaScenaRoot.getStylesheets().add(SupportControllers.class.getResource("/rubrica/CSS/DarkMode.css").toExternalForm());
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) elementoFxml.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo per lnciare gli alert
    public static void showAlert(String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}
