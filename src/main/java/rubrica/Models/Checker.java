/**
 * @file Checker.java
 *
 * @brief Classe per la validazione dei campi di un contatto.
 *
 * Questa classe fornisce funzionalità per validare i campi "telefono" ed "email" di un contatto.
 *
 * @author Gabriele Pannuto
 * @date December 07, 2024
 * @version 2.0
 */

package rubrica.Models;

import java.util.regex.*;

public class Checker {


    /**
     * @brief Effettua il controllo sulla validità dei campi "telefono" ed "email" di un contatto
     *
     * @param[in] c Il contatto da validare
     *
     * @pre Il contatto deve avere almeno nome e/o cognome settati.
     *
     * @post Viene stabilito se i campi "telefono" ed "email" del contatto sono legittimi.
     *
     * @return Ritorna un valore true se il contatto è valido, altrimenti false.
     */
    public boolean validaContatto(Contatto c) {

        for (int i = 0; i < c.getNumeriTelefono().size(); ++i) {
            if (!c.getNumeriTelefono().get(i).matches("^\\d{10}$"))
                return false;
        }

        for (int j = 0; j < c.getEmail().size(); ++j) {
            if (!c.getEmail().get(j).matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
                return false;
        }

        return true;
    }
}
