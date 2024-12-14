/**
 * @file FileManager.java
 *
 * @brief Classe per la gestione dell'importazione ed esportazione della rubrica.
 *
 * Questa classe fornisce funzionalità per importare ed esportare la rubrica da e verso file .vcf.
 *
 * @author Gabriele Pannuto
 * @date December 07, 2024
 * @version 2.0
 */


package rubrica.Models;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Email;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Rubrica r;

    /**
     * @brief Costruttore della classe FileManager.
     *
     * @param[in] r Il riferimento alla rubrica.
     *
     * @post crea un oggetto FileManager con un riferimento alla rubrica.
     */
    public FileManager(Rubrica r) {
        this.r = r;
    }

    /**
     * @brief Importa una rubrica da file .vcf.
     *
     * @param[in] path È il percorso verso il file .vcf che contiene la rubrica da importare.
     *
     * @return 'true' se la rubrica è stata importata con successo, 'false' altrimenti.
     *
     * @pre Il path si riferisce ad un file .vcf.
     * @post La rubrica viene popolata a partire dal file.
     **/
    public boolean importaRubrica(String path) {
        try {
            File file = new File(path);
            List<VCard> vCards = new ArrayList<>(Ezvcard.parse(file).all()); //Analizza il file .vcf per ottenere tutti gli oggetti VCard in una ArrayList.

            for (VCard vCard : vCards) {
                Contatto c = importaVCard(vCard);
                this.r.aggiungiContatto(c);
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'importa rubrica: " + e.getMessage());
            return false;
        }

        return true;
    }

    /*
     * Importa un contatto da un oggetto VCard.
     *
     * vCard L'oggetto VCard da cui importare i dati del contatto.
     * Un nuovo oggetto Contatto con i dati importati dalla VCard.
     *
     * L'oggetto VCard deve contenere dati validi per un contatto.
     * Viene creato un nuovo oggetto Contatto con i dati della VCard.
     */
    private Contatto importaVCard(VCard vCard) {
        String nome;
        if (vCard.getStructuredName().getGiven() == null)
            nome = "";
        else
            nome = vCard.getStructuredName().getGiven();

        String cognome;
        if (vCard.getStructuredName().getFamily() == null)
            cognome = "";
        else
            cognome = vCard.getStructuredName().getFamily();


        List<String> numTel = new ArrayList<>();
        for (Telephone tel : vCard.getTelephoneNumbers()) {
            numTel.add(tel.getText());
        }

        List<String> mails = new ArrayList<>();
        for (Email mail : vCard.getEmails()) {
            mails.add(mail.getValue());
        }

        String dataCreazione = vCard.getExtendedProperty("X-DATA-CREAZIONE").getValue();

        String note;
        if (vCard.getNotes() != null)
            note = vCard.getNotes().getFirst().getValue();
        else
            note = null;

        boolean isPreferito = Boolean.parseBoolean(vCard.getExtendedProperty("X-IS-PREFERITO").getValue());


        return new Contatto(nome, cognome, numTel, mails, dataCreazione, note, isPreferito);
    }


    /**
     * @brief Esporta la rubrica su file .vcf.
     *
     * @param[in] path È il percorso verso la directory in cui scrivere il file .vcf.
     *
     * @return 'true' se la rubrica è stata esportata con successo, 'false' altrimenti.
     *
     * @pre Il path si riferisce a una directory.
     * @post la rubrica viene salvata in un file .vcf nella directory selezionata.
     */
    public boolean esportaRubrica(String path) {

        File file = new File(path,"rubrica.vcf");
        List<VCard> vCards = new ArrayList<>();

        List<Contatto> contatti = this.r.getContatti();
        for(int i = 0; i <contatti.size(); i++) {
            Contatto contatto = contatti.get(i);
            VCard vCard = esportaContatto(contatto);
            vCards.add(vCard);
        }

        try {
            Ezvcard.write(vCards).go(file);     //Scrive la lista di VCard nel file specificato
        } catch (IOException e) {
            System.err.println("Errore durante l'esporta rubrica: " + e.getMessage());
            return false;
        }

        return true;
    }


    /*
     *  Esporta un contatto in un oggetto VCard.
     *
     *  L'oggetto VCard contenente i dati del contatto.
     *
     *  Il contatto si trova in rubrica
     *  Viene restituita una VCard con i dati del contatto.
     */
    private VCard esportaContatto(Contatto c) {
        VCard vCard = new VCard();

        if (c.getNome() == null) {
            StructuredName structuredName = new StructuredName();
            structuredName.setFamily(c.getCognome());
            vCard.setStructuredName(structuredName);
        } else if (c.getCognome() == null) {
            StructuredName structuredName = new StructuredName();
            structuredName.setGiven(c.getNome());
            vCard.setStructuredName(structuredName);
        } else {
            StructuredName structuredName = new StructuredName();
            structuredName.setGiven(c.getNome());
            structuredName.setFamily(c.getCognome());
            vCard.setStructuredName(structuredName);
        }

        if (c.getNumeriTelefono() != null) {
            for (String numero : c.getNumeriTelefono()) {
                vCard.addTelephoneNumber(numero);
            }
        }

        if (c.getEmail() != null) {
            for (String mail : c.getEmail()) {
                vCard.addEmail(mail);
            }
        }

        vCard.addExtendedProperty("X-DATA-CREAZIONE", c.getDataCreazione());

        vCard.addNote(c.getNota());

        vCard.addExtendedProperty("X-IS-PREFERITO", Boolean.toString(c.getIsPreferito()));


        return vCard;
    }
}