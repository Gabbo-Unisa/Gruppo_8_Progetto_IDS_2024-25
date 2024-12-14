package rubrica;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rubrica.Models.Checker;
import rubrica.Models.Contatto;
import rubrica.Models.FileManager;
import rubrica.Models.Rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private FileManager fileManager;
    private Rubrica rubrica;

    @BeforeEach
    void setUp() {
        // Inizializza due oggetti, Rubrica e FileManager, prima di ogni test
        rubrica = new Rubrica(new Checker());
        fileManager = new FileManager(rubrica);
    }

    @AfterEach
    void tearDown() {
        // Pulizia dopo ogni test
        rubrica = null;
        fileManager = null;
    }

    @Test
    void testImportaRubrica() throws IOException {

        // Test per verificare il metodo importaRubrica

        // Caso 1: Importa da file valido
        File fileTemporaneo = File.createTempFile("valid",".vcf");
        fileTemporaneo.deleteOnExit();

        try (FileWriter fw = new FileWriter(fileTemporaneo)) {
            fw.write("BEGIN:VCARD\n");
            fw.write("VERSION:3.0\n");
            fw.write("PRODID:ez-vcard 0.11.0\n");
            fw.write("N:Pannuto;Gabriele\n");
            fw.write("TEL:3923546722\n");
            fw.write("EMAIL:gpannuto@gmail.com\n");
            fw.write("X-DATA-CREAZIONE:11 dicembre 2024\n");
            fw.write("X-IS-PREFERITO:true\n");
            fw.write("NOTE:Nota3\n");
            fw.write("END:VCARD\n");
        }

        boolean risultatiValidi = fileManager.importaRubrica(fileTemporaneo.getAbsolutePath());

        List<Contatto> contatti = rubrica.getContatti();

        // Verifico che l'importazione sia riuscita
        assertTrue(risultatiValidi);
        assertEquals(1, contatti.size());

        Contatto contattoImportato = contatti.get(0);
        assertEquals("Gabriele",contattoImportato.getNome());
        assertEquals("Pannuto",contattoImportato.getCognome());
        assertTrue(contattoImportato.getIsPreferito());

        // Caso 2: Importa da file non esistente
        boolean risultatiNonValidi = fileManager.importaRubrica("nonesiste.vcf");

        // Verifico che l'importazione sia fallita
        assertFalse(risultatiNonValidi,"Il file non esiste quindi l'importazione non dovrebbe avere successo.");
    }

    @Test
    void testEsportaRubrica() throws IOException {
        //Caso 1: Esporta rubrica con contatti
        List<String> telefoni = new ArrayList<>();
        telefoni.add("3923546722");
        Contatto contatto = new Contatto("Mario", "Bianchi", telefoni, null,"11 dicembre 2024", "Nota1",true);
        rubrica.aggiungiContatto(contatto);

        //Crea una directory temporanea per l'esportazione
        File dirTemporanea = new File(System.getProperty("java.io.tmpdir"));
        boolean risultati = fileManager.esportaRubrica(dirTemporanea.getAbsolutePath());

        //Verifica che l'esportazione sia riuscita e che il file esportato esista
        assertTrue(risultati, "L'esportazione della rubrica con contatti dovrebbe riuscire.");
        File fileOutput = new File(dirTemporanea, "output.vcf");
        assertTrue(fileOutput.exists(), "Il file esportato non esiste.");

        //Verifica il contenuto del file esportato per vedere se contiene il contatto
        List<VCard> contattiEsportati = Ezvcard.parse(fileOutput).all();
        assertEquals(1, contattiEsportati.size(), "Il numero di contatti esportati non è corretto.");
        VCard contattoEsportato = contattiEsportati.get(0);

        //Verifico se il nome del contatto esportato sia corretto
        assertEquals("Mario", contattoEsportato.getStructuredName().getGiven(), "Il nome del contatto esportato non corrisponde.");

        //Verifico se il cognome del contatto esportato sia corretto
        assertEquals("Bianchi", contattoEsportato.getStructuredName().getFamily(), "Il cognome del contatto esportato non corrisponde.");

        //Verifico se gli eventuali numeri di telefono del contatto esportato sia corretto
        boolean telefonoTrovato = false;
        if (contattoEsportato.getTelephoneNumbers() != null) {
            for (ezvcard.property.Telephone tel : contattoEsportato.getTelephoneNumbers()) {
                if ("3923546722".equals(tel.getText())) {
                    telefonoTrovato = true;
                    break;
                }
            }
        }
        assertTrue(telefonoTrovato, "Il numero di telefono del contatto esportato non corrisponde.");

        //Verifico se le eventuali email del contatto esportato siano corrette
        boolean emailTrovata = false;
        if (contattoEsportato.getEmails() != null) {
            for (ezvcard.property.Email email : contattoEsportato.getEmails()) {
                if ("mario.bianchi@example.com".equals(email.getValue())) {
                    emailTrovata = true;
                    break;
                }
            }
        }
        assertFalse(emailTrovata, "L'email del contatto esportato non corrisponde.");

        //Verifica se la data di creazione del contatto esportato sia corretta
        boolean dataTrovata = false;
        if (contattoEsportato.getExtendedProperties("X-DATA-CREAZIONE") != null) {
            for (ezvcard.property.RawProperty prop : contattoEsportato.getExtendedProperties("X-DATA-CREAZIONE")) {
                if ("11 dicembre 2024".equals(prop.getValue())) {
                    dataTrovata = true;
                    break;
                }
            }
        }
        assertTrue(dataTrovata, "La data di creazione del contatto esportato non corrisponde.");

        //Verifica se il contatto esportato sia marcato come preferito
        boolean preferitoTrovato = false;
        if (contattoEsportato.getExtendedProperties("X-IS-PREFERITO") != null) {
            for (ezvcard.property.RawProperty prop : contattoEsportato.getExtendedProperties("X-IS-PREFERITO")) {
                if ("true".equals(prop.getValue())) {
                    preferitoTrovato = true;
                    break;
                }
            }
        }
        assertTrue(preferitoTrovato, "Il contatto esportato non è marcato come preferito.");

        //Caso 2: Esporta rubrica vuota
        rubrica.eliminaContatto(contatto);
        boolean risultatiVuoti = fileManager.esportaRubrica(dirTemporanea.getAbsolutePath());

        //Verifica che l'esportazione di una rubrica vuota sia riuscita
        assertTrue(risultatiVuoti, "L'esportazione della rubrica vuota dovrebbe riuscire.");

        //Verifica che il file esportato esista anche per una rubrica vuota
        File fileOutputVuoto = new File(dirTemporanea, "output.vcf");
        assertTrue(fileOutputVuoto.exists(), "Il file esportato con una rubrica vuota non esiste.");
        fileOutputVuoto.deleteOnExit();

        //Controlla che il file non contenga contatti (file vuoto)
        List<VCard> listaVuota = Ezvcard.parse(fileOutputVuoto).all();
        assertEquals(0, listaVuota.size(), "Il file esportato di una rubrica vuota non dovrebbe contenere contatti.");
    }
}