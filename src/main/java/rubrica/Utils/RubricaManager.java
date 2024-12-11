package rubrica.Utils;

import rubrica.Models.Rubrica;

public class RubricaManager {
    private static Rubrica rubrica;

    public static void inizializza(Rubrica r) {
        RubricaManager.rubrica = r;
    }

    public static Rubrica getRubrica() {
        return RubricaManager.rubrica;
    }
}
