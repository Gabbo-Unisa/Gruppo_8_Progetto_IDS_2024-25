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
        checker = new Checker();
    }

    @AfterEach
    void tearDown() {
        checker = null;
    }

    @Test
    void testvalidaContatto() {
        //Caso 1: contatto valido
        List<String> numeriValidi = List.of("3404657894","3319875463");
        List<String> emailValide = List.of("caso1@example.it","user1@libero.it");
        Contatto contattoValido = new Contatto("Mario","Rossi", numeriValidi, emailValide,"7 dicembre 2024","Nota1",false);
        assertTrue(checker.validaContatto(contattoValido),"Il contatto dovrebbe essere passare la validazione.");

        //Caso 2: Nome e Cognome non validi
        Contatto nomeCognomeNonValidi = new Contatto("123", "@@@", new ArrayList<>(), new ArrayList<>(), "10 novembre 2024","Nota2", false);
        assertFalse(checker.validaContatto(nomeCognomeNonValidi),"Il contatto con nome e cognome non validi non dovrebbe esssere valido.");

        //Caso 3: Numeri di telefono non validi
        List<String> numeriNonValidi = List.of("456","abc");
        Contatto telefonoNonValido = new Contatto("Luigi","Bianchi", numeriNonValidi, new ArrayList<>(), "11 dicembre 2024", "Nota3", false);
        assertFalse(checker.validaContatto(telefonoNonValido),"Il contatto con numeri di telefono non validi non dovrebbe essere valido.");

        //Caso 4: Email non valide
        List<String> emailNonValide = List.of("io@","@example.it","no.it");
        Contatto emailNonValideContatto = new Contatto("Marco", "Verdi", new ArrayList<>(), emailNonValide, "4 dicembre 2024","Nota4",false);
        assertFalse(checker.validaContatto(emailNonValideContatto), "Il contatto con email non valide non dovrebbe essere valido.");

        //Caso 5: Contatto senza numeri di telefono ed email ma con nome/cognome validi
        Contatto senzaTelefonoEmail = new Contatto("Giulia", "Neri", null, null,"11 dicembre 2024", "Nota5", false);
        assertTrue(checker.validaContatto(senzaTelefonoEmail), "Il contatto senza numeri di telefono ed email dovrebbe essere valido poichè nome/cognome sono validi.");

        //Caso 6: Contatto senza numeri di telefono ed email ma con nome/cognome non validi
        Contatto senzaTelefonoEmail2 = new Contatto("1", ".", null, null,"8 dicembre 2024", "Nota6",false);
        assertFalse(checker.validaContatto(senzaTelefonoEmail2),"Il contatto senza numero di telefono ed email non dovrebbe essere valido poichè nome/cognome non sono validi");

        //Contatto 7: Nome e cognome null
        Contatto nomeCognomeNull = new Contatto(null, null, new ArrayList<>(), new ArrayList<>(),"3 novembre 2024", "Nota6", false);
        assertFalse(checker.validaContatto(nomeCognomeNull), "Il contatto con nome e cognome nulli non dovrebbe essere valido.");
    }
}