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
        checker = new Checker();
        rubrica = new Rubrica(checker);
    }

    @AfterEach
    void tearDown() {
        rubrica = null;
        checker = null;
    }

    @Test
    void testGetContatti() {
        assertTrue(rubrica.getContatti().isEmpty(), "La lista dei contatti dovrebbe essere vuota all'inizio.");
        Contatto contatto = new Contatto("Mario","Rossi", new ArrayList<>(), new ArrayList<>(), "2024-12-11", "Nota1", false);
        rubrica.aggiungiContatto(contatto);

        List<Contatto> contatti = rubrica.getContatti();
        assertEquals(1,contatti.size(),"La rubrica dovrebbe contenere un contatto");
        assertTrue(contatti.contains(contatto),"Il contatto aggiunto dovrebbe essere presente");
    }

    @Test
    void testAggiungiContatto() {
        Contatto contatto1 = new Contatto("Mario", "Rossi", new ArrayList<>(List.of("1234567898")), new ArrayList<>(List.of("mario.rossi@outlook.it")), "11 Dicembre 2024","Nota1",false);
        Contatto contatto2 = new Contatto("Mario", "Rossi", new ArrayList<>(List.of("1234567898")), new ArrayList<>(List.of("mario.rossi@outlook.it")), "11 Dicembre 2024","Nota2",false);

        //Tentativo aggiunta di un contatto
        assertTrue(rubrica.aggiungiContatto(contatto1), "Il contatto valido dovrebbe essere aggiunto con successo.");
        //Tentativo aggiunta di un contatto duplicato
        assertFalse(rubrica.aggiungiContatto(contatto2),"Il contatto duplicato non dovrebbe essere aggiunto.");

        //Tentativo aggiunta contatto con email non valida
        Contatto contatto3 = new Contatto("Luigi","Bianchi",new ArrayList<>(List.of("9876543212")), new ArrayList<>(List.of("email_non_valida")),"4 Dicembre 2024", "Nota3",false);
        assertFalse(rubrica.aggiungiContatto(contatto3),"Il contatto con email non valida non dovrebbe essere aggiunto.");

        //Tentativo aggiunta contatto con numero di telefono non valido
        Contatto contatto4 = new Contatto("Anna", "Verdi", new ArrayList<>(List.of("numero_non_valido")), new ArrayList<>(List.of("anna.verdi@gmail.com")), "8 Dicembre 2024", "Nota4", false);
        assertFalse(rubrica.aggiungiContatto(contatto4),"Il contatto con numero di telefono non valido non dovrebbe essere aggiunto.");

        //Tentativo di aggiunta contatto senza nome e cognome
        Contatto contatto5 = new Contatto("", "", new ArrayList<>(List.of("1234567894")), new ArrayList<>(List.of("noname@libero.it")), "9 Dicembre 2024", "Nota5", false );
        assertFalse(rubrica.aggiungiContatto(contatto5), "Il contatto senza nome e cognome non dovrebbe essere aggiunto.");

        //Tentativo di aggiunta contatto con solo nome
        Contatto contatto6 = new Contatto("Francesco", "", new ArrayList<>(List.of("1274849306")), new ArrayList<>(List.of("onlyname@example.com")),"1 Dicembre 2024","Nota6",true);
        assertTrue(rubrica.aggiungiContatto(contatto6),"Il contatto con solo nome dovrebbe essere aggiunto con successo.");

        //Tentativo di aggiunta contatto con solo cognome
        Contatto contatto7 = new Contatto("", "Esposito", new ArrayList<>(List.of("1239876546")), new ArrayList<>(List.of("onlysurname@example.it")),"15 Novembre 2024","Nota7",true);
        assertTrue(rubrica.aggiungiContatto(contatto7), "Il contatto con solo cognome dovrebbe essere aggiunto con successo.");

        //Tentativo di aggiunta di un contatto senza passaggio della data di creazione
        Contatto contatto8 = new Contatto("Gabriele", "Pannuto", new ArrayList<>(List.of("3409871232")), new ArrayList<>(List.of("nodata@example.it")),"","Nota8",false);
        assertTrue(rubrica.aggiungiContatto(contatto8),"Il contatto senza data dovrebbe essere aggiunto con successo con data corrente.");

    }

    @Test
    void testModificaContatto() {
        Contatto oldContatto = new Contatto("Luigi", "Bianchi", new ArrayList<>(), new ArrayList<>(), "11 Dicembre 2024", "Note",false);
        Contatto updateContatto = new Contatto("Luigi", "Bianchi", new ArrayList<>(List.of("3367228745")), new ArrayList<>(), "11 Dicembre 2024", "Note",true);

        rubrica.aggiungiContatto(oldContatto);

        //Modifica il contatto
        assertTrue(rubrica.modificaContatto(oldContatto,updateContatto));

        //Verifica che l'operazione di modifica sia avvenuta con successo
        assertTrue(rubrica.getContatti().contains(updateContatto));
        assertFalse(rubrica.getContatti().contains(oldContatto));

    }

    @Test
    void testEliminaContatto() {
        Contatto contatto = new Contatto("Andrea", "Iannone", new ArrayList<>(List.of("3445589222")), new ArrayList<>(), "7 Novembre 2024","Nota", false);
        rubrica.aggiungiContatto(contatto);

        //Elimina contatto esistente
        rubrica.eliminaContatto(contatto);
        assertFalse(rubrica.getContatti().contains(contatto),"Il contatto eliminato non dovrebbe essere più presente nella rubrica.");
    }

    @Test
    void testRicercaContatti() {
        Contatto contatto = new Contatto("Ferdinando", "Paparo", new ArrayList<>(List.of("3927896543")), new ArrayList<>(),"3 Ottobre 2024", "Nota",false);
        rubrica.aggiungiContatto(contatto);

        //Ricerca nella rubrica
        List<Contatto> risultati = rubrica.ricercaContatti("Ferdinando");
        assertEquals(1, risultati.size(),"La ricerca dovrebbe restituire come risultato un solo contatto");
        assertTrue(risultati.contains(contatto),"Il contatto dovrebbe essere incluso nell'elenco di contatti trovati.");

        //Ricerca senza risultati
        List<Contatto> risultativuoti = rubrica.ricercaContatti("Mario");
        assertTrue(risultativuoti.isEmpty(),"La ricerca non dovrebbe restituire nessun risultato ");
    }

    @Test
    void testGetContattiPreferiti() {
        Contatto preferito = new Contatto("Mario", "Rossi", new ArrayList<>(), new ArrayList<>(), "11 Dicembre 2024", "Nota1", true);
        Contatto nonPreferito = new Contatto("Luigi", "Verdi", new ArrayList<>(), new ArrayList<>(), "7 Dicembre 2024", "Nota2", false);

        rubrica.aggiungiContatto(preferito);
        rubrica.aggiungiContatto(nonPreferito);

        //Recupero dei contatti preferiti
        List<Contatto> contattipreferiti = rubrica.getContattiPreferiti();
        assertEquals(1, contattipreferiti.size(), "Dovrebbe essere un solo contatto nell'elenco preferiti.");
        assertTrue(contattipreferiti.contains(preferito), "Il contatto preferito dovrebbe esssere incluso nell'elenco preferiti.");
        assertFalse(contattipreferiti.contains(nonPreferito), "Il contatto nonPreferito non dovrebbe essere incluso nell'elenco preferiti.");
    }
}