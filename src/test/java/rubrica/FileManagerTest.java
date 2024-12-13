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
        rubrica = new Rubrica(new Checker());
        fileManager = new FileManager(rubrica);
    }

    @AfterEach
    void tearDown() {
        rubrica = null;
        fileManager = null;
    }

    @Test
    void testimportaRubrica() throws IOException {
        //Caso 1: Importa da file valido
        File filetemporaneo = File.createTempFile("valid",".vcf");
        filetemporaneo.deleteOnExit();

        try (FileWriter fw = new FileWriter(filetemporaneo)) {
            fw.write("BEGIN:VCARD\n");
            fw.write("VERSION:3.0\n");
            fw.write("PRODID:ez-vcard 0.11.0\n");
            fw.write("N:Pannuto;Gabriele\n");
            fw.write("X-DATA-CREAZIONE:11 dicembre 2024\n");
            fw.write("X-IS-PREFERITO:true\n");
            fw.write("NOTE:Nota3\n");
            fw.write("END:VCARD\n");
        }

        boolean risultativalidi = fileManager.importaRubrica(filetemporaneo.getAbsolutePath());

        /*
            memorizzo la lista dei contatti così che non richiamo sempre la funzione getContatti che ha una
            complessità O(n).
         */
        List<Contatto> contatti = rubrica.getContatti();

        //Verifico che l'importazione sia riuscita
        assertTrue(risultativalidi);
        assertEquals(1, contatti.size());

        Contatto contattoimportato = contatti.get(0);
        assertEquals("Gabriele",contattoimportato.getNome());
        assertEquals("Pannuto",contattoimportato.getCognome());
        assertTrue(contattoimportato.getIsPreferito());

        //Caso 2: Importa da file non esistente
        boolean risultatiNonValidi = fileManager.importaRubrica("nonesiste.vcf");

        //Verifico che l'importazione sia fallita
        assertFalse(risultatiNonValidi,"Il file non esiste quindi l'importazione non dovrebbe avere successo.");
    }

    @Test
    void testesportaRubrica() throws IOException {
        //Caso 1: Esporta rubrica con contatti
        List<String> telefoni = new ArrayList<>();
        telefoni.add("3923546722");
        Contatto contatto = new Contatto("Mario", "Bianchi", telefoni, null,"11 dicembre 2024", "Nota1",true);
        rubrica.aggiungiContatto(contatto);

        //Crea una directory temporanea per l'esportazione
        File dirtemporanea = new File(System.getProperty("java.io.tmpdir"));
        boolean risultati = fileManager.esportaRubrica(dirtemporanea.getAbsolutePath());

        //Verifica che l'esportazione sia riuscita e che il file esportato esista
        assertTrue(risultati, "L'esportazione della rubrica con contatti dovrebbe riuscire.");
        File fileoutput = new File(dirtemporanea, "output.vcf");
        assertTrue(fileoutput.exists(), "Il file esportato non esiste.");

        //Verifica il contenuto del file esportato per vedere se contiene il contatto
        List<VCard> contattiEsportati = Ezvcard.parse(fileoutput).all();
        assertEquals(1, contattiEsportati.size(), "Il numero di contatti esportati non è corretto.");
        VCard contattoesportato = contattiEsportati.get(0);
        assertEquals("Mario", contattoesportato.getStructuredName().getGiven(), "Il nome del contatto esportato non corrisponde.");
        assertEquals("Bianchi", contattoesportato.getStructuredName().getFamily(), "Il cognome del contatto esportato non corrisponde.");
        assertTrue(contattoesportato.getTelephoneNumbers().stream()
                        .anyMatch(tel -> tel.getText().equals("3923546722")),
                "Il numero di telefono del contatto esportato non corrisponde.");

        // Verifica che la data di creazione del contatto esportato sia corretta
        assertTrue(contattoesportato.getExtendedProperties("X-DATA-CREAZIONE").stream()
                        .anyMatch(prop -> "11 dicembre 2024".equals(prop.getValue())),
                "La data di creazione del contatto esportato non corrisponde.");

        // Verifica che il contatto esportato sia marcato come preferito
        assertTrue(contattoesportato.getExtendedProperties("X-IS-PREFERITO").stream()
                        .anyMatch(prop -> "true".equals(prop.getValue())), "Il contatto esportato non è marcato come preferito.");

        //Caso 2: Esporta rubrica
        rubrica.eliminaContatto(contatto);
        boolean risultativuoti = fileManager.esportaRubrica(dirtemporanea.getAbsolutePath());

        //Verifica che l'esportazione di una rubrica vuota sia riuscita
        assertTrue(risultativuoti, "L'esportazione della rubrica vuota dovrebbe riuscire.");

        //Verifica che il file esportato esista anche per una rubrica vuota
        File fileoutputvuoto = new File(dirtemporanea, "output.vcf");
        assertTrue(fileoutputvuoto.exists(), "Il file esportato con una rubrica vuota non esiste.");
        fileoutputvuoto.deleteOnExit();

        //Controlla che il file non contenga contatti (file vuoto)
        List<VCard> listavuota = Ezvcard.parse(fileoutputvuoto).all();
        assertEquals(0, listavuota.size(), "Il file esportato di una rubrica vuota non dovrebbe contenere contatti.");
    }
}