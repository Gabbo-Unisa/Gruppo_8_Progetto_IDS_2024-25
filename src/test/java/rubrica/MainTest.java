package rubrica;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.engine.discovery.DiscoverySelectors;

public class MainTest {

    public static void runAllTests() {
        // Crea una richiesta di scoperta che seleziona tutte le classi di test
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectPackage("test.java.rubrica"))  // Seleziona tutte le classi di test
                    .build();

        // Crea il launcher per eseguire i test
        Launcher launcher = LauncherFactory.create();

        // Esegui i test
        launcher.execute(request);
    }

    public static void main(String[] args) {
        // Esegui tutti i test
        runAllTests();
    }
}