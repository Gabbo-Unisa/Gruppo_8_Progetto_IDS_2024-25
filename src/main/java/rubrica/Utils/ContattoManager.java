/*
 * Questa classe permette di mantenere un riferimento a un contatto in memoria statica
 * così da consentirne l'accesso in qualsiasi punto dell'applicazione.
 * Risolve il problema della comunicazione tra i vari controller
 * nel momento in cui si crea/visualizza/modifica/elimina un contatto.
*/


package rubrica.Utils;

import rubrica.Models.Contatto;

public class ContattoManager {
    private static Contatto contatto;

    public static void setContatto(Contatto c) {
        ContattoManager.contatto = c;
    }

    public static Contatto getContatto() {
        return ContattoManager.contatto;
    }
}
