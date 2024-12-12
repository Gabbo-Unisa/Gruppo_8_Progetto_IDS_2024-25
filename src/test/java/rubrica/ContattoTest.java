package rubrica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rubrica.Models.Contatto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContattoTest {

    private Contatto contatto;

    @BeforeEach
    void setUp() {
        // Inizializza un oggetto Contatto prima di ogni test
        List<String> numeri = Arrays.asList("3886186445", "3335207835");
        List<String> email = Arrays.asList("mario03@gmail.com", "mrossi@libero.it");
        contatto = new Contatto("Mario", "Rossi", numeri, email, "Nota di test", true);
    }

    @AfterEach
    void tearDown() {
        // Pulizia dopo ogni test
        contatto = null;
    }

    @Test
    void setNome() {
        // Test per verificare il metodo setNome
        contatto.setNome("Luigi"); // Imposta un nuovo nome
        assertEquals("Luigi", contatto.getNome()); // Verifica che il nome sia stato aggiornato correttamente
    }

    @Test
    void setCognome() {
        // Test per verificare il metodo setCognome
        contatto.setCognome("Verdi"); // Imposta un nuovo cognome
        assertEquals("Verdi", contatto.getCognome()); // Verifica che il cognome sia stato aggiornato correttamente
    }

    @Test
    void setNumeriTelefono() {
        // Test per verificare il metodo setNumeriTelefono
        List<String> nuoviNumeri = Arrays.asList("3312867744", "3385683977");
        contatto.setNumeriTelefono(nuoviNumeri); // Imposta una nuova lista di numeri
        assertEquals(nuoviNumeri, contatto.getNumeriTelefono()); // Verifica che i numeri siano stati aggiornati
    }

    @Test
    void setEmail() {
        // Test per verificare il metodo setEmail
        List<String> nuoveEmail = Arrays.asList("mariomario@gmail.com");
        contatto.setEmail(nuoveEmail); // Imposta una nuova lista di email
        assertEquals(nuoveEmail, contatto.getEmail()); // Verifica che le email siano state aggiornate
    }

    @Test
    void setNota() {
        // Test per verificare il metodo setNota
        contatto.setNota("Nuova nota"); // Imposta una nuova nota
        assertEquals("Nuova nota", contatto.getNota()); // Verifica che la nota sia stata aggiornata
    }

    @Test
    void setPreferito() {
        // Test per verificare il metodo setPreferito
        contatto.setPreferito(false); // Cambia lo stato di preferito
        assertFalse(contatto.getIsPreferito()); // Verifica che il contatto non sia più preferito
    }

    @Test
    void getNome() {
        // Test per verificare il metodo getNome
        assertEquals("Mario", contatto.getNome()); // Verifica che il nome sia corretto
    }

    @Test
    void getCognome() {
        // Test per verificare il metodo getCognome
        assertEquals("Rossi", contatto.getCognome()); // Verifica che il cognome sia corretto
    }

    @Test
    void getNumeriTelefono() {
        // Test per verificare il metodo getNumeriTelefono
        List<String> numeriAttesi = Arrays.asList("3886186445", "3335207835");
        assertEquals(numeriAttesi, contatto.getNumeriTelefono()); // Verifica che i numeri siano corretti
    }

    @Test
    void getEmail() {
        // Test per verificare il metodo getEmail
        List<String> emailAttese = Arrays.asList("mario03@gmail.com", "mrossi@libero.it");
        assertEquals(emailAttese, contatto.getEmail()); // Verifica che le email siano corrette
    }

    @Test
    void getDataCreazione() {
        // Test per verificare il metodo getDataCreazione
        assertNotNull(contatto.getDataCreazione()); // Verifica che la data di creazione non sia nulla
    }

    @Test
    void getNota() {
        // Test per verificare il metodo getNota
        assertEquals("Nota di test", contatto.getNota()); // Verifica che la nota sia corretta
    }

    @Test
    void getIsPreferito() {
        // Test per verificare il metodo getIsPreferito
        assertTrue(contatto.getIsPreferito()); // Verifica che il contatto sia preferito
    }

    @Test
    void compareTo() {
        // Test per verificare il metodo compareTo
        Contatto altroContatto = new Contatto("Luigi", "Bianchi", Arrays.asList(), Arrays.asList(), "", false);
        assertTrue(contatto.compareTo(altroContatto) > 0); // Verifica che Mario Rossi sia dopo Luigi Bianchi in ordine alfabetico
        assertTrue(altroContatto.compareTo(contatto) < 0); // Verifica che Luigi Bianchi sia prima di Mario Rossi
        Contatto stessoContatto = new Contatto("Mario", "Rossi", Arrays.asList(), Arrays.asList(), "", false);
        assertEquals(0, contatto.compareTo(stessoContatto)); // Verifica che due contatti identici siano considerati uguali
    }

    @Test
    void testToString() {
        // Test per verificare il metodo toString
        String expected = "Contatto{nome='Mario', cognome='Rossi', numeriTelefono=[3886186445, 3335207835], email=[mario03@gmail.com, mrossi@libero.it], dataCreazione='" + contatto.getDataCreazione() + "', nota='Nota di test', isPreferito=true}";
        assertEquals(expected, contatto.toString()); // Verifica che la rappresentazione in stringa sia corretta
    }
}