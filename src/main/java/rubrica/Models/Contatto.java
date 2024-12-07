/**
 * @file Contatto.java
 *
 * @brief Classe per la gestione dei contatti.
 *
 * Questa classe rappresenta un contatto all'interno della rubrica, con informazioni
 * come nome, cognome, numeri di telefono, email, immagine, data di creazione, note e preferenze.
 *
 * @author Carmine Terracciano
 * @date December 07, 2024
 * @version 2.0
 */


package rubrica.Models;

import ezvcard.VCard;
import ezvcard.property.Photo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Contatto implements Serializable, Comparable<Contatto>{

    private String nome;
    private String cognome;
    private List<String> telefono;
    private List<String> email;
    private Photo immagine;
    private final Date dataCreazione;
    private String nota;
    private boolean isPreferito;

    /**
     * @brief Costruttore della classe Contatto.
     *
     * @param[in] nome Il nome del contatto.
     * @param[in] cognome Il cognome del contatto.
     * @param[in] telefono La lista dei numeri di telefono del contatto.
     * @param[in] email La lista degli indirizzi email del contatto.
     * @param[in] immagine La foto del contatto.
     * @param[in] nota La nota associate al contatto.
     * @param[in] isPreferito Indica se il contatto è contrassegnato come preferito.
     */
    public Contatto(String nome, String cognome, List<String> telefono,
                    List<String> email, Photo immagine,
                    String nota, boolean isPreferito) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.immagine = immagine;
        this.dataCreazione = new Date();
        this.nota = nota;
        this.isPreferito = isPreferito;
    }


    /**
     * @brief Imposta il nome del contatto.
     *
     * @param[in] nome Il nome del contatto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief Imposta il cognome del contatto.
     *
     * @param[in] cognome Il cognome del contatto.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @brief Imposta la lista dei numeri di telefono del contatto.
     *
     * @param[in] telefono La lista dei numeri di telefono del contatto.
     */
    public void setTelefono(List<String> telefono) {
        this.telefono = telefono;
    }

    /**
     * @brief Imposta la lista degli indirizzi email del contatto.
     *
     * @param[in] email La lista degli indirizzi email del contatto.
     */
    public void setEmail(List<String> email) {
        this.email = email;
    }

    /**
     * @brief Imposta l'immagine del contatto.
     *
     * @param[in] immagine L'immagine del contatto.
     */
    public void setImmagine(Photo immagine) {
        this.immagine = immagine;
    }


    /**
     * @brief Imposta la nota associata al contatto.
     *
     * @param[in] nota La nota associata al contatto.
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    /**
     * @brief Imposta se il contatto è contrassegnato come preferito.
     *
     * @param[in] isPreferito Indica se il contatto è contrassegnato come preferito.
     */
    public void setPreferito(boolean preferito) {
        isPreferito = preferito;
    }


    /**
     * @brief Restituisce il nome del contatto.
     *
     * @return Il nome del contatto.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @brief Restituisce il cognome del contatto.
     *
     * @return Il cognome del contatto.
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * @brief Restituisce la lista dei numeri di telefono del contatto.
     *
     * @return La lista dei numeri di telefono del contatto.
     */
    public List<String> getTelefono() {
        return this.telefono;
    }

    /**
     * @brief Restituisce la lista degli indirizzi email del contatto.
     *
     * @return La lista degli indirizzi email del contatto.
     */
    public List<String> getEmail() {
        return this.email;
    }

    /**
     * @brief Restituisce l'immagine del contatto.
     *
     * @return L'immagine del contatto.
     */
    public Photo getImmagine() {
        return this.immagine;
    }

    /**
     * @brief Restituisce la data di creazione del contatto.
     *
     * @return La data di creazione del contatto.
     */
    public Date getDataCreazione() {
        return this.dataCreazione;
    }

    /**
     * @brief Restituisce la nota associata al contatto.
     *
     * @return La nota associata al contatto.
     */
    public String getNota() {
        return this.nota;
    }

    /**
     * @brief Restituisce se il contatto è contrassegnato come preferito.
     *
     * @return true se il contatto è contrassegnato come preferito, altrimenti false.
     */
    public boolean getIsPreferito() {
        return this.isPreferito;
    }



    /**
     * @brief Importa un contatto da un oggetto VCard.
     *
     * @param[in] vCard L'oggetto VCard da cui importare i dati del contatto.
     *
     * @return Un nuovo oggetto Contatto con i dati importati dalla VCard.
     *
     * @pre L'oggetto VCard deve contenere dati validi per un contatto.
     * @post Viene creato un nuovo oggetto Contatto con i dati della VCard.
     */
    public Contatto importaVCard(VCard vCard){
        return null;
    }

    /**
     * @brief Esporta un contatto in un oggetto VCard.
     *
     * @return L'oggetto VCard contenente i dati del contatto.
     *
     * @pre Il contatto deve avere almeno nome e/o cognome settati.
     * @post Viene restituita una VCard con i dati del contatto.
     */
    public VCard esportaContatto(){
        return null;
    }

    /**
     * @brief Confronta due contatti in ordine alfabetico.
     *
     * @param[in] c Il contatto con cui confrontare l'oggetto.
     *
     * @return Un valore intero:
     * - Minore di 0 se il contatto corrente precede il contatto c
     * - Uguale a 0 se i contatti sono omonimi
     * - Maggiore di 0 se il contatto corrente segue il contatto c
     */
    @Override
    public int compareTo(Contatto c) {
        return 0;
    }
}
