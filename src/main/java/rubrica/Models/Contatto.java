/**
 * @file Contatto.java
 *
 * @brief Classe per la gestione dei contatti.
 *
 * Questa classe rappresenta un contatto all'interno della rubrica, con le relative informazioni
 * quali nome, cognome, numeri di telefono, email, data di creazione, note e preferenze.
 *
 * @author Carmine Terracciano
 * @date December 07, 2024
 */


package rubrica.Models;

import ezvcard.VCard;
import ezvcard.property.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contatto implements Comparable<Contatto>{
    private String nome;
    private String cognome;
    private List<String> numeriTelefono;
    private List<String> email;
    private final String dataCreazione;
    private String nota;
    private boolean isPreferito;

    /**
     * @brief Costruttore della classe Contatto - genera la data di creazione automaticamente.
     *
     * @param[in] nome Il nome del contatto.
     * @param[in] cognome Il cognome del contatto.
     * @param[in] numeriTelefono La lista dei numeri di telefono del contatto.
     * @param[in] email La lista degli indirizzi email del contatto.
     * @param[in] nota La nota associate al contatto.
     * @param[in] isPreferito Indica se il contatto è contrassegnato come preferito.
     */
    public Contatto(String nome, String cognome, List<String> numeriTelefono,
                    List<String> email,
                    String nota, boolean isPreferito) {

        this.nome = (nome == null) ? "" : nome.trim();
        this.cognome = (cognome == null) ? "" : cognome.trim();

        this.numeriTelefono = new ArrayList<>(numeriTelefono);
        this.email = new ArrayList<>(email);

        Date data = new Date();     // Catturo la Data attuale
        SimpleDateFormat formatoData = new SimpleDateFormat("dd MMMM yyyy");    //Imposto il formato della data
        this.dataCreazione = formatoData.format(data);

        this.nota = nota;
        this.isPreferito = isPreferito;
    }

    /**
     * @brief Costruttore della classe Contatto - la data di creazione è passata come parametro.
     *
     * @param[in] nome Il nome del contatto.
     * @param[in] cognome Il cognome del contatto.
     * @param[in] numeriTelefono La lista dei numeri di telefono del contatto.
     * @param[in] email La lista degli indirizzi email del contatto.
     * @param[in] dataCreazione La data di creazione del contatto.
     * @param[in] nota La nota associate al contatto.
     * @param[in] isPreferito Indica se il contatto è contrassegnato come preferito.
     */
    public Contatto(String nome, String cognome, List<String> numeriTelefono,
                    List<String> email, String dataCreazione,
                    String nota, boolean isPreferito) {

        this.nome = (nome == null) ? "" : nome.trim();
        this.cognome = (cognome == null) ? "" : cognome.trim();

        this.numeriTelefono = new ArrayList<>(numeriTelefono);
        this.email = new ArrayList<>(email);

        this.dataCreazione = dataCreazione;
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
     * @param[in] numeriTelefono La lista dei numeri di telefono del contatto.
     */
    public void setNumeriTelefono(List<String> numeriTelefono) {
        this.numeriTelefono = numeriTelefono;
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
    public List<String> getNumeriTelefono() {
        return this.numeriTelefono;
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
     * @brief Restituisce la data di creazione del contatto.
     *
     * @return La data di creazione del contatto.
     */
    public String getDataCreazione() {
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
        String nomeCognome = this.nome + this.cognome;

        String cNomeCognome = c.getNome() + c.getCognome();

        return nomeCognome.compareTo(cNomeCognome);
    }


    /**
     * @brief Restituisce una rappresentazione in formato stringa del contatto.
     *
     * @return Una stringa che rappresenta il contatto con tutte le sue proprietà.
     */
    @Override
    public String toString() {
        return "Contatto{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", numeriTelefono=" + numeriTelefono +
                ", email=" + email +
                ", dataCreazione='" + dataCreazione + '\'' +
                ", nota='" + nota + '\'' +
                ", isPreferito=" + isPreferito +
                '}';
    }
}