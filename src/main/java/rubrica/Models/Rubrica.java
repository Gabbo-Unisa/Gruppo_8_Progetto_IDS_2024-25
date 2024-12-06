/**
 * @file Rubrica.java
 *
 * @brief Classe principale per la gestione della rubrica.
 *
 * Questo file contiene la definizione della classe Rubrica, che fornisce
 * funzionalità per gestire un elenco di contatti. Le operazioni supportate
 * includono l'aggiunta, la modifica, la rimozione, la ricerca di contatti
 * e la gestione dei contatti preferiti.
 *
 * @author Ferdinando Paparo
 * @date December 06, 2024
 * @version 2.0
 */


package rubrica.Models;


import java.util.Set;
import java.util.TreeSet;

public class Rubrica {
    Set<Contatto> contatti = new TreeSet<>();

    /**
     * @brief Aggiunge un nuovo contatto alla rubrica.
     *
     * @param[in] c Il contatto da aggiungere alla rubrica.
     *
     * @pre Il contatto deve avere almeno nome e/o cognome settati.
     *
     * @post Se i dati del contatto sono validi, il contatto viene aggiunto alla rubrica.
     */
    public void aggiungiContatto(Contatto c){

    }


    /**
     * @brief Modifica le informazioni di un contatto esistente nella rubrica.
     *
     * @param[in] old Il contatto attualmente presente nella rubrica che deve essere modificato.
     * @param[in] update Il nuovo contatto contenente i dati aggiornati.
     *
     * @pre Il contatto 'old' deve esistere nella rubrica.
     *      Il contatto 'update' deve avere almeno nome e/o cognome settati.
     *
     * @post Se i dati del contatto 'update' sono validi, il contatto 'old' viene sostituito con 'update'.
     */
    public void modificaContatto(Contatto old, Contatto update){

    }


    /**
     * @brief Rimuove un contatto dalla rubrica.
     *
     * @param[in] c Il contatto da rimuovere.
     *
     * @pre Il contatto deve esistere nella rubrica.
     *
     * @post Il contatto specificato viene rimosso dalla rubrica.
     */
    public void eliminaContatto(Contatto c){

    }


    /**
     * @brief Cerca contatti nella rubrica che corrispondono a una query.
     *
     * @param[in] query Una stringa utilizzata per cercare i contatti in rubrica.
     *
     * @return Una collezione ordinata di contatti che soddisfano i criteri di ricerca.
     *
     * @pre La query non deve essere vuota.
     *
     * @post Viene restituita una collezione di contatti, o una collezione vuota
     *       se nessun contatto soddisfa i criteri.
     */
    public Set<Contatto> ricercaContatti(String query){
        return null;
    }


    /**
     * @brief Restituisce tutti i contatti marcati come preferiti.
     *
     * @return Una collezione ordinata contenente i contatti contrassegnati come preferiti.
     *
     * @pre La rubrica deve contenere almeno un contatto.
     *
     * @post Restituisce una collezione con i contatti preferiti.
     *       Se non ci sono contatti preferiti, restituisce una collezione vuota.
     */
    public Set<Contatto> getContattiPreferiti(){
        return null;
    }

}
