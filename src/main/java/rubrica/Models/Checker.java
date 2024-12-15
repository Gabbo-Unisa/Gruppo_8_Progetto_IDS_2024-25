/**
 * @file Checker.java
 *
 * @brief Classe per la validazione dei campi di un contatto.
 *
 * Questa classe fornisce funzionalità per validare i campi "telefono" ed "email" di un contatto.
 *
 * @author Gabriele Pannuto
 * @date December 07, 2024
 */

package rubrica.Models;



public class Checker {


    /**
     * @brief Effettua un controllo sui dati di un contatto. Per essere valido, il contatto deve avere nome e/o cognome
     * composti da sole lettere e spazi, con una lunghezza massima di 15 caratteri. I numeri di telefono devono essere
     * composti da 10 cifre, mentre gli indirizzi email devono seguire il formato standard (esempio@dominio.com).
     *
     * @param[in] c Il contatto da validare.
     *
     * @return Ritorna un valore 'true' se il contatto è valido, altrimenti 'false'.
     *
     * @pre Il contatto deve avere almeno nome e/o cognome settati.
     * @post Viene stabilito se i campi del contatto sono legittimi.
     */
    public boolean validaContatto(Contatto c) {

        String nome = c.getNome();
        String cognome = c.getCognome();

        if(nome.isEmpty() && cognome.isEmpty())
            return false;
        /*Da qui in poi nome e cognome non possono essere entrambi vuoti: controllo i vari casi*/
        if(nome.isEmpty()) {    //Nome vuoto
            if (!cognome.matches("^[\\p{L} ]{1,15}$"))  //Cognome non valido
                return false;
        }
        else {  //Nome non vuoto
            if(cognome.isEmpty()) { //Cognome vuoto
                if (!nome.matches("^[\\p{L} ]{1,15}$")) //Nome non valido
                    return false;
            }
            else    //Nome e Cognome non vuoti
                if(!nome.matches("^[\\p{L} ]{1,15}$") || !cognome.matches("^[\\p{L} ]{1,15}$"))
                    return false;
        }

        if(c.getNumeriTelefono() != null) {
            for(String numero : c.getNumeriTelefono()) {
                if(!numero.matches("^\\d{10}$"))
                    return false;
            }
        }

        if(c.getEmail() != null) {
            for(String email : c.getEmail()) {
                if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
                    return false;
            }
        }

        return true;
    }
}