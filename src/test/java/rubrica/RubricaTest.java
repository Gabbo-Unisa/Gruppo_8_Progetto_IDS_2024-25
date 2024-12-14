package rubrica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rubrica.Models.Checker;
import rubrica.Models.Contatto;
import rubrica.Models.Rubrica;

import java.util.ArrayList;
import java.util.List;

class RubricaTest {

    private Rubrica rubrica;
    private Checker checker;

    @BeforeEach
    void setUp() {
        // Inizializza due oggetti, Checker e Rubrica, prima di ogni test
        checker = new Checker();
        rubrica = new Rubrica(checker);
    }

    @AfterEach
    void tearDown() {
        // Pulizia dopo ogni test
        rubrica = null;
        checker = null;
    }

    @Test
    void testGetContatti() {

        // Test per verificare il metodo getContattiPreferiti

        // La lista dei contatti dovrebbe essere vuota all'inizio
        assertTrue(rubrica.getContatti().isEmpty());

        Contatto contatto = new Contatto("Mario","Rossi", new ArrayList<>(), new ArrayList<>(), "1 dicembre 2024", "Nota1", false);
        rubrica.aggiungiContatto(contatto);

        List<Contatto> contatti = rubrica.getContatti();

        // La lista dovrebbe contenere un solo contatto e dovrebbe essere quello aggiunto precedentemente
        assertEquals(1,contatti.size());
        assertTrue(contatti.contains(contatto));
    }

    @Test
    void testAggiungiContatto() {

        // Test per verificare il metodo aggiungiContatto

        Contatto contatto1 = new Contatto("Mario", "Rossi", new ArrayList<>(List.of("1234567898")), new ArrayList<>(List.of("mario.rossi@outlook.it")), "11 dicembre 2024","Nota1",false);
        Contatto contatto2 = new Contatto("Mario", "Rossi", new ArrayList<>(List.of("1234567898")), new ArrayList<>(List.of("mario.rossi@outlook.it")), "11 dicembre 2024","Nota2",false);

        // Caso 1: Il contatto valido, con sia nome e cognome, e non duplicato dovrebbe essere aggiunto con successo
        assertTrue(rubrica.aggiungiContatto(contatto1));

        // Caso 2: Il contatto duplicato non dovrebbe essere aggiunto
        assertFalse(rubrica.aggiungiContatto(contatto2));

        // Caso 3: Il contatto valido, con solo nome, e non duplicato dovrebbe essere aggiunto con successo
        Contatto contatto3 = new Contatto("Francesco", "", new ArrayList<>(List.of("1274849306")), new ArrayList<>(List.of("francesco@gmail.com")),"1 dicembre 2024","Nota3",true);
        assertTrue(rubrica.aggiungiContatto(contatto3));

        // Caso 4: Il contatto valido, con solo cognome, e non duplicato dovrebbe essere aggiunto con successo
        Contatto contatto4 = new Contatto("", "Esposito", new ArrayList<>(List.of("1239876546")), new ArrayList<>(List.of("esposito@libero.it")),"15 novembre 2024","Nota4",true);
        assertTrue(rubrica.aggiungiContatto(contatto4));

        // Caso 5: Il contatto senza nome e cognome non dovrebbe essere aggiunto
        Contatto contatto5 = new Contatto("", "", new ArrayList<>(List.of("1234567894")), new ArrayList<>(List.of("nessuno@libero.it")), "9 dicembre 2024", "Nota5", false );
        assertFalse(rubrica.aggiungiContatto(contatto5));

        // Caso 6: Il contatto con numero di telefono non valido non dovrebbe essere aggiunto
        Contatto contatto6 = new Contatto("Anna", "Verdi", new ArrayList<>(List.of("numero_non_valido")), new ArrayList<>(List.of("anna.verdi@gmail.com")), "8 dicembre 2024", "Nota6", false);
        assertFalse(rubrica.aggiungiContatto(contatto6));

        // Caso 7: Il contatto con email non valida non dovrebbe essere aggiunto
        Contatto contatto7 = new Contatto("Luigi","Bianchi",new ArrayList<>(List.of("9876543212")), new ArrayList<>(List.of("email_non_valida")),"4 dicembre 2024", "Nota7",false);
        assertFalse(rubrica.aggiungiContatto(contatto7));

        // Caso 8: Il contatto senza data dovrebbe essere aggiunto con successo con data corrente
        Contatto contatto8 = new Contatto("Gabriele", "Pannuto", new ArrayList<>(List.of("3409871232")), new ArrayList<>(List.of("gpannuto@hotmail.com")),"","Nota8",false);
        assertTrue(rubrica.aggiungiContatto(contatto8));

    }

    @Test
    void testModificaContatto() {

        // Test per verificare il metodo modificaContatto

        // Popolo la rubrica con due contatti per il testing
        Contatto oldContatto = new Contatto("Luigi", "Bianchi", new ArrayList<>(), new ArrayList<>(), "11 dicembre 2024", "Nota1",false);
        rubrica.aggiungiContatto(oldContatto);
        Contatto contattoInserito = new Contatto("Francesco", "Rossi", new ArrayList<>(), new ArrayList<>(), "11 dicembre 2024", "Nota2",true);
        rubrica.aggiungiContatto(contattoInserito);


        // Caso 1: Contatto aggiornato già esistente
        Contatto contattoDuplicato = new Contatto("Francesco", "Rossi", new ArrayList<>(), new ArrayList<>(), "11 dicembre 2024", "Nota4",true);
        assertFalse(rubrica.modificaContatto(oldContatto, contattoDuplicato));
        assertTrue(rubrica.getContatti().contains(oldContatto));
        assertFalse(rubrica.getContatti().contains(contattoDuplicato));

        // Caso 2: Contatto non valido
        Contatto contattoInvalido = new Contatto("Ferdinando13", null, new ArrayList<>(), new ArrayList<>(), "11 dicembre 2024", "Nota5",false);
        assertFalse(rubrica.modificaContatto(oldContatto, contattoInvalido));
        assertTrue(rubrica.getContatti().contains(oldContatto));
        assertFalse(rubrica.getContatti().contains(contattoInvalido));

        // Caso 3: Modifica del contatto andata a buon fine
        Contatto updateContatto = new Contatto("Franco", "Verdi", new ArrayList<>(List.of("3367228745")), new ArrayList<>(), "11 dicembre 2024", "Nota3",true);
        assertTrue(rubrica.modificaContatto(oldContatto, updateContatto));
        assertTrue(rubrica.getContatti().contains(updateContatto));
        assertFalse(rubrica.getContatti().contains(oldContatto));
    }

    @Test
    void testEliminaContatto() {

        // Test per verificare il metodo eliminaContatto

        Contatto contatto = new Contatto("Andrea", "Iannone", new ArrayList<>(List.of("3445589222")), new ArrayList<>(), "7 novembre 2024","Nota", false);
        rubrica.aggiungiContatto(contatto);

        // Il contatto eliminato non dovrebbe essere più presente nella rubrica
        rubrica.eliminaContatto(contatto);
        assertFalse(rubrica.getContatti().contains(contatto));
    }

    @Test
    void testRicercaContatti() {

        // Test per verificare il metodo ricercaContatti

        Contatto contatto = new Contatto("Ferdinando", "Paparo", new ArrayList<>(List.of("3927896543")), new ArrayList<>(),"3 ottobre 2024", "Nota",false);
        rubrica.aggiungiContatto(contatto);

        // Caso 1: Ricerca con risultati
        List<Contatto> risultati = rubrica.ricercaContatti("Ferdinando");

        // La ricerca dovrebbe restituire come risultato un solo contatto che dovrebbe essere incluso nell'elenco di contatti trovati
        assertEquals(1, risultati.size());
        assertTrue(risultati.contains(contatto));

        // Caso 2: Ricerca senza risultati
        List<Contatto> risultatiVuoti = rubrica.ricercaContatti("Mario");

        // La ricerca non dovrebbe restituire nessun risultato
        assertTrue(risultatiVuoti.isEmpty());
    }

    @Test
    void testGetContattiPreferiti() {

        // Test per verificare il metodo getContattiPreferiti

        Contatto preferito = new Contatto("Mario", "Rossi", new ArrayList<>(), new ArrayList<>(), "11 dicembre 2024", "Nota1", true);
        Contatto nonPreferito = new Contatto("Luigi", "Verdi", new ArrayList<>(), new ArrayList<>(), "7 dicembre 2024", "Nota2", false);

        rubrica.aggiungiContatto(preferito);
        rubrica.aggiungiContatto(nonPreferito);

        // Recupero dei contatti preferiti
        List<Contatto> contattiPreferiti = rubrica.getContattiPreferiti();

        // Ci dovrebbe essere un solo contatto nell'elenco preferiti
        assertEquals(1, contattiPreferiti.size());

        // Il contatto preferito dovrebbe esssere incluso nell'elenco preferiti
        assertTrue(contattiPreferiti.contains(preferito));

        // Il contatto nonPreferito non dovrebbe essere incluso nell'elenco preferiti
        assertFalse(contattiPreferiti.contains(nonPreferito));
    }
}