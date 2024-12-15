package rubrica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rubrica.Models.Checker;
import rubrica.Models.Contatto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {

    private Checker checker;

    @BeforeEach
    void setUp() {
        // Inizializza un oggetto Checker prima di ogni test
        checker = new Checker();
    }

    @AfterEach
    void tearDown() {
        // Pulizia dopo ogni test
        checker = null;
    }

    @Test
    void testValidaContatto() {

        // Test per verificare il metodo validaContatto

        List<String> numeriValidi = List.of("3404657894","3319875463"); // Lista di numeri validi
        List<String> emailValide = List.of("mrossi@libero.it","bianchi13@hotmail.com"); // Lista di email valide
        List<String> numeriNonValidi = List.of("4ab","3347865498", "3319456"); // Lista di numeri non validi
        List<String> emailNonValide = List.of("marcoverdi@libero.it","@io","carlo@gmail.com"); // Lista di email non valide

        // Caso 1: Contatto con -> nome: valido; cognome: valido; numeri: validi; email: valide -> Valido
        Contatto contattoValido = new Contatto("Mario","Rossi", numeriValidi, emailValide,"7 dicembre 2024","Nota1",true);
        assertTrue(checker.validaContatto(contattoValido));

        // Caso 2: Contatto con -> nome: valido; cognome: valido; numeri: validi; email: non valide -> Non Valido
        Contatto emailNonValideContatto = new Contatto("Marco", "Verdi", numeriValidi, emailNonValide, "4 dicembre 2024","Nota2",false);
        assertFalse(checker.validaContatto(emailNonValideContatto));

        //Caso 3: Contatto con -> nome: valido; cognome: valido; numeri: non validi; email: valide -> Non Valido
        Contatto numeriNonValidiContatto = new Contatto("Luigi","Bianchi", numeriNonValidi, emailValide, "11 dicembre 2024", "Nota3", true);
        assertFalse(checker.validaContatto(numeriNonValidiContatto));

        // Caso 4: Contatto con -> nome: valido; cognome: non valido; numeri: validi; email: valide -> Non Valido
        Contatto cognomeNonValido = new Contatto("Carlo", "Ner1", numeriValidi, emailValide, "10 novembre 2024","Nota4", false);
        assertFalse(checker.validaContatto(cognomeNonValido));

        // Caso 5: Contatto con -> nome: non valido; cognome: valido; numeri: validi; email: valide -> Non Valido
        Contatto nomeNonValido = new Contatto("Ma@@0", "Rossi", numeriValidi, emailValide, "25 Febbraio 2024","Nota5", true);
        assertFalse(checker.validaContatto(nomeNonValido));

        // Caso 6: Contatto con -> nome: valido; cognome: valido; numeri: assenti; email: assenti -> Valido
        Contatto emailNumeriAssenti = new Contatto("Marco", "Verdi", new ArrayList<>(), new ArrayList<>(),"12 Maggio 2024", "Nota6",false);
        assertTrue(checker.validaContatto(emailNumeriAssenti));

        // Caso 7: Contatto con -> nome: nullo; cognome: valido; numeri: validi; email: assenti -> Valido
        Contatto nomeNulloCognomeValido = new Contatto(null, "Bianchi", numeriValidi, new ArrayList<>(),"4 Luglio 2024", "Nota7",false);
        assertTrue(checker.validaContatto(nomeNulloCognomeValido));

        // Caso 8: Contatto con -> nome: nullo; cognome: non valido; numeri: validi; email: validi -> Non Valido
        Contatto nomeNulloCognomeNonValido = new Contatto(null, "N3r.", numeriValidi, emailValide,"18 Luglio 2024", "Nota8",true);
        assertFalse(checker.validaContatto(nomeNulloCognomeNonValido));

        // Caso 9: Contatto con -> nome: valido; cognome: nullo; numeri: assenti; email: valide -> Valido
        Contatto nomeValidoCognomeNullo = new Contatto("Mario", null, new ArrayList<>(), emailValide,"28 Agosto 2024", "Nota9",true);
        assertTrue(checker.validaContatto(nomeValidoCognomeNullo));

        // Caso 10: Contatto con -> nome: non valido; cognome: nullo; numeri: validi; email: validi -> Non Valido
        Contatto nomeNonValidoCognomeNullo = new Contatto("M4r/o", null, numeriValidi, emailValide,"14 Aprile 2024", "Nota10",false);
        assertFalse(checker.validaContatto(nomeNonValidoCognomeNullo));

        // Caso 11: Contatto con -> nome: nullo; cognome: nullo; numeri: validi; email: validi -> Non Valido
        Contatto nomeCognomeNulli = new Contatto(null, null, numeriValidi, emailValide,"2 Giugno 2024", "Nota11",true);
        assertFalse(checker.validaContatto(nomeCognomeNulli));
    }
}