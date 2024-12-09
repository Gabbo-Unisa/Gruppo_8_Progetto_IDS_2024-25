package rubrica.Controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupportControllers {
    private static boolean displayMode = false;

    /*public SupportDisplayMode() {}*/

    public static boolean getDisplayMode() {
        return SupportControllers.displayMode;
    }

    public static void setDisplayMode(boolean displayMode) {
        SupportControllers.displayMode = displayMode;
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
            nuovaScenaRoot.getStylesheets().add(SupportControllers.class.getResource("/CSS/DarkMode.css").toExternalForm());
            Scene nuovaScena = new Scene(nuovaScenaRoot);

            Stage stage = (Stage) elementoFxml.getScene().getWindow();
            stage.setScene(nuovaScena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
