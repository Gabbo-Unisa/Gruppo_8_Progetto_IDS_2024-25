/*
 * Questa classe permette di mantenere un riferimento alla rubrica in memoria statica
 * così da consentirne l'accesso in qualsiasi punto dell'applicazione.
 * Risolve il problema della comunicazione tra i vari controller
 * nel momento in cui si apportano modifiche alla rubrica.
 */

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

    public static void eliminaContatto() {
        RubricaManager.getRubrica().eliminaContatto(ContattoManager.getContatto());

    }
}
