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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Rubrica implements Serializable {
    private Set<Contatto> contatti;
    private Checker checker;

    /**
     * @brief Costruttore della classe Rubrica.
     *
     * @param[in] checker Un oggetto Checker utilizzato per validare i dati dei contatti.
     *
     * @post Crea un nuovo oggetto Rubrica con un insieme vuoto di contatti.
     */
    public Rubrica(Checker checker) {
        this.contatti = new TreeSet<>();
        this.checker = checker;
    }


    /**
     * @brief Restituisce l'insieme dei contatti presenti nella rubrica.
     *
     * @return Una collezione iterabile contenente tutti i contatti della rubrica.
     *
     * @pre La rubrica deve essere stata inizializzata.
     *
     * @post Viene restituito l'insieme dei contatti presenti nella rubrica.
     */
    public List<Contatto> getContatti() {
        return new ArrayList<>(this.contatti);
    }

    /**
     * @brief Aggiunge un nuovo contatto alla rubrica.
     *
     * @param[in] c Il contatto da aggiungere alla rubrica.
     * @return 'true' se il contatto è stato aggiunto con successo, 'false' altrimenti.
     *
     * @pre Il contatto deve avere almeno nome e/o cognome settati.
     * @post Se i dati del contatto sono validi, il contatto viene aggiunto alla rubrica.
     */
    public boolean aggiungiContatto(Contatto c) {
        if(checker.validaContatto(c)) {
            contatti.add(c);
            return true;
        }

        return false;
    }


    /**
     * @brief Modifica le informazioni di un contatto esistente nella rubrica.
     *
     * @param[in] old Il contatto attualmente presente nella rubrica che deve essere modificato.
     * @param[in] update Il nuovo contatto contenente i dati aggiornati.
     *
     * @return 'true' se la modifica è stata effettuata con successo, 'false' altrimenti.
     *
     * @pre Il contatto 'old' deve esistere nella rubrica.
     *      Il contatto 'update' deve avere almeno nome e/o cognome settati.
     *
     * @post Se i dati del contatto 'update' sono validi, il contatto 'old' viene sostituito con 'update'.
     */
    public boolean modificaContatto(Contatto old, Contatto update) {
        if(checker.validaContatto(update)) {
            contatti.remove(old);
            contatti.add(update);
            return true;
        }

        return false;
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
        contatti.remove(c);
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
    public List<Contatto> ricercaContatti(String query) {
        List<Contatto> cRicercati = new ArrayList<>();

        for(Contatto c : this.contatti) {
            if(c.getNome().matches("^" + query + ".*") || c.getCognome().matches("^" + query + ".*"))
                cRicercati.add(c);
        }

        return cRicercati;
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
    public List<Contatto> getContattiPreferiti(){
        List<Contatto> cPreferiti = new ArrayList<>();

        for(Contatto c : this.contatti) {
            if(c.getIsPreferito())
                cPreferiti.add(c);
        }

        return cPreferiti;
    }

    /**
     * @brief Restituisce una rappresentazione in formato stringa della rubrica.
     *
     * @return Una stringa che rappresenta la rubrica con tutti i suoi contatti.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Contatto c : this.contatti) {
            sb.append(c.toString()).append("\n");
        }

        return sb.toString();
    }
}