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

        // Creazione di un file temporaneo
        File fileTemporaneo1 = File.createTempFile("valid",".vcf");

        // Garantisce che il file temporaneo venga rimosso alla fine del test
        fileTemporaneo1.deleteOnExit();

        // Scrittura di due vCard valide all'interno del file
        try (FileWriter fw = new FileWriter(fileTemporaneo1)) {

            // Prima vCard
            fw.write("BEGIN:VCARD\n");
            fw.write("VERSION:3.0\n");
            fw.write("PRODID:ez-vcard 0.11.0\n");
            fw.write("N:Pannuto;Gabriele\n");
            fw.write("TEL:3923546722\n");
            fw.write("X-DATA-CREAZIONE:11 dicembre 2024\n");
            fw.write("X-IS-PREFERITO:true\n");
            fw.write("NOTE:Nota1\n");
            fw.write("END:VCARD\n");

            // Seconda vCard
            fw.write("BEGIN:VCARD\n");
            fw.write("VERSION:3.0\n");
            fw.write("PRODID:ez-vcard 0.11.0\n");
            fw.write("N:Paparo;Ferdinando\n");
            fw.write("EMAIL:fpaparo@gmail.com\n");
            fw.write("X-DATA-CREAZIONE:2 Giugno 2024\n");
            fw.write("X-IS-PREFERITO:true\n");
            fw.write("NOTE:Nota2\n");
            fw.write("END:VCARD\n");
        }

        // Importazione del file nella rubrica e assegnazione dell'esito a 'risultatiValidi'
        boolean risultatiValidi = fileManager.importaRubrica(fileTemporaneo1.getAbsolutePath());

        // Verifico che l'importazione sia riuscita
        assertTrue(risultatiValidi);

        // Assegno a 'contatti1' la lista dei contatti nella rubrica
        List<Contatto> contatti1 = rubrica.getContatti();

        // Verifico che 'contatti1' abbia esattamente due contatti
        assertEquals(2, contatti1.size());

        // Verifico che i contatti importati abbiano i dati corretti
        Contatto contattoImportato1 = contatti1.get(0);
        assertEquals("Ferdinando",contattoImportato1.getNome());
        assertEquals("Paparo",contattoImportato1.getCognome());
        assertEquals(1, contattoImportato1.getEmail().size());
        assertEquals("fpaparo@gmail.com", contattoImportato1.getEmail().get(0));
        assertEquals("2 Giugno 2024",contattoImportato1.getDataCreazione());
        assertTrue(contattoImportato1.getIsPreferito());

        Contatto contattoImportato2 = contatti1.get(1);
        assertEquals("Gabriele",contattoImportato2.getNome());
        assertEquals("Pannuto",contattoImportato2.getCognome());
        assertEquals(1, contattoImportato2.getNumeriTelefono().size()); // Controllo dimensione lista
        assertEquals("3923546722", contattoImportato2.getNumeriTelefono().get(0)); // Controllo contenuto
        assertEquals("11 dicembre 2024",contattoImportato2.getDataCreazione());
        assertTrue(contattoImportato2.getIsPreferito());


        // Caso 2: Importa da file vuoto

        // Rimozione dei contatti nella rubrica
        rubrica.eliminaContatto(contattoImportato1);
        rubrica.eliminaContatto(contattoImportato2);

        // Creazione di un file temporaneo
        File fileTemporaneo2 = File.createTempFile("vuoto",".vcf");

        // Garantisce che il file temporaneo venga rimosso alla fine del test
        fileTemporaneo2.deleteOnExit();

        // Importazione del file vuoto nella rubrica e assegnazione dell'esito a 'risultatiVuoti'
        boolean risultatiVuoti = fileManager.importaRubrica(fileTemporaneo2.getAbsolutePath());

        // Verifico che l'importazione sia riuscita
        assertTrue(risultatiVuoti);

        // Assegno a 'contatti2' la lista dei contatti nella rubrica
        List<Contatto> contatti2 = rubrica.getContatti();

        // Verifico che 'contatti2' abbia esattamente 0 contatti
        assertEquals(0, contatti2.size());


        // Caso 3: Importa da file non conforme allo standard vcf

        // Creazione di un file temporaneo
        File fileTemporaneo3 = File.createTempFile("invalid",".vcf");

        // Garantisce che il file temporaneo venga rimosso alla fine del test
        fileTemporaneo3.deleteOnExit();

        // Scrittura di una vCard non valida all'interno del file
        try (FileWriter fw = new FileWriter(fileTemporaneo3)) {

            // Prima vCard
            fw.write("BEGIN:VCARD\n");
            fw.write("VERSION:3.0\n");
            fw.write("PRODID:ez-vcard 0.11.0\n");
            fw.write("N:Pannuto;Gabriele\n");
            fw.write("TEL:3923546722\n");
            fw.write("EMAIL:gpannuto@gmail.com\n");
            fw.write("X-DATA-CREAZIONE:11 dicembre 2024\n");
            fw.write("X-IS-PREFERITO:true\n");
            fw.write("NOTE:Nota1\n");
            fw.write("END:VCARD\n");

            // Seconda vCard
            fw.write("BEGIN:VCARD\n");
            fw.write("VERSION:3.0\n");
            fw.write("PRODID:ez-vcard 0.11.0\n");
            fw.write("Nome:Paparo;Ferdinando\n");   // Formato errato, 'Nome:' al posto di 'N:'
            fw.write("TEL:3887496135\n");
            fw.write("EMAIL:fpaparo@gmail.com\n");
            fw.write("X-DATA-CREAZIONE:2 Giugno 2024\n");
            fw.write("X-IS-PREFERITO:true\n");
            fw.write("NOTE:Nota2\n");
            fw.write("END:VCARD\n");
        }

        // Importazione del file nella rubrica e assegnazione dell'esito a 'risultatiNonValidi'
        boolean risultatiNonValidi = fileManager.importaRubrica(fileTemporaneo3.getAbsolutePath());

        // L'importazione fallisce non appena si incontra una vCard col formato non valido
        assertFalse(risultatiNonValidi);

        // Assegno a 'contatti3' la lista dei contatti nella rubrica
        List<Contatto> contatti3 = rubrica.getContatti();

        // Verifico che 'contatti3' abbia esattamente 1 contatto
        assertEquals(1, contatti3.size());

        // Verifico che il contatto valido importato abbia i dati corretti
        Contatto contattoImportato3 = contatti3.get(0);
        assertEquals("Gabriele",contattoImportato3.getNome());
        assertEquals("Pannuto",contattoImportato3.getCognome());
        assertEquals(1, contattoImportato3.getNumeriTelefono().size()); // Controllo dimensione lista
        assertEquals("3923546722", contattoImportato3.getNumeriTelefono().get(0)); // Controllo contenuto
        assertEquals(1, contattoImportato3.getEmail().size());
        assertEquals("gpannuto@gmail.com", contattoImportato3.getEmail().get(0));
        assertEquals("11 dicembre 2024",contattoImportato3.getDataCreazione());
        assertTrue(contattoImportato3.getIsPreferito());
    }

    @Test
    void testEsportaRubrica() throws IOException {

        // Test per verificare il metodo esportaRubrica

        //Caso 1: Esporta rubrica con contatti

        // Creazione di un oggetto Contatto
        List<String> numeriAttesi = List.of("3923546722","3401234567","3335207835");
        List<String> emailAttese = List.of("mario@gmail.com","mrossi@libero.it");
        Contatto contatto = new Contatto("Mario", "Bianchi", numeriAttesi, emailAttese,"11 dicembre 2024", "Nota1",true);

        // Aggiunta del contatto alla rubrica
        rubrica.aggiungiContatto(contatto);

        // Crea una directory temporanea per l'esportazione
        File dirTemporanea = new File(System.getProperty("java.io.tmpdir"));

        // Esportazione della rubrica nel file e assegnazione dell'esito a 'risultati'
        boolean risultati = fileManager.esportaRubrica(dirTemporanea.getAbsolutePath());

        // Verifico che l'esportazione sia riuscita
        assertTrue(risultati);

        // Creazione del file di output
        File fileOutput = new File(dirTemporanea, "rubrica.vcf");

        // Verifico che il file 'output.vcf' esista
        assertTrue(fileOutput.exists());

        // Garantisce che il file temporaneo venga rimosso alla fine del test
        fileOutput.deleteOnExit();

        // Verifico il contenuto del file esportato per vedere se contiene un solo contatto
        List<VCard> contattiEsportati = Ezvcard.parse(fileOutput).all();
        assertEquals(1, contattiEsportati.size());

        // Verifico che il contatto esportato abbia i dati corretti
        VCard contattoEsportato = contattiEsportati.get(0);
        assertEquals("Mario", contattoEsportato.getStructuredName().getGiven());  // Verifico se il nome è corretto
        assertEquals("Bianchi", contattoEsportato.getStructuredName().getFamily());  // Verifico se il cognome è corretto

        // Lista dei numeri di telefono esportati
        List<String> numeriEsportati = new ArrayList<>();
        if (contattoEsportato.getTelephoneNumbers() != null) {
            for (ezvcard.property.Telephone tel : contattoEsportato.getTelephoneNumbers()) {
                numeriEsportati.add(tel.getText());
            }
        }

        // Verifica che ogni numero atteso sia presente
        for (String numeroAtteso : numeriAttesi) {
            assertTrue(numeriEsportati.contains(numeroAtteso));
        }

        // Verifica che non ci siano numeri in più
        assertEquals(numeriAttesi.size(), numeriEsportati.size());


        // Lista delle email esportate
        List<String> emailEsportate = new ArrayList<>();
        if (contattoEsportato.getEmails() != null) {
            for (ezvcard.property.Email email : contattoEsportato.getEmails()) {
                emailEsportate.add(email.getValue());
            }
        }

        // Verifica che ogni email attesa sia presente
        for (String emailAttesa : emailAttese) {
            assertTrue(emailEsportate.contains(emailAttesa));
        }

        // Verifica che non ci siano email in più
        assertEquals(emailAttese.size(), emailEsportate.size());

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
        assertTrue(dataTrovata); // Verifico che la data di creazione corrisponde

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
        assertTrue(preferitoTrovato);  // Verifico che sia marcato come preferito


        //Caso 2: Esporta rubrica vuota

        // Rimozione del contatto nella rubrica
        rubrica.eliminaContatto(contatto);

        // Esportazione della rubrica nel file e assegnazione dell'esito a 'risultatiVuoti'
        boolean risultatiVuoti = fileManager.esportaRubrica(dirTemporanea.getAbsolutePath());

        // Verifico che l'esportazione di una rubrica vuota sia riuscita
        assertTrue(risultatiVuoti);

        // Verifico che il file esportato esista anche per una rubrica vuota
        File fileOutputVuoto = new File(dirTemporanea, "rubrica.vcf");
        assertTrue(fileOutputVuoto.exists());

        // Garantisce che il file temporaneo venga rimosso alla fine del test
        fileOutputVuoto.deleteOnExit();

        // Controllo che il file non contenga contatti (file vuoto)
        List<VCard> listaVuota = Ezvcard.parse(fileOutputVuoto).all();
        assertEquals(0, listaVuota.size());
    }
}