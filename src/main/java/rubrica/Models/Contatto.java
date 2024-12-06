package rubrica.Models;

import ezvcard.VCard;
import ezvcard.property.Photo;

import java.util.Date;
import java.util.List;

public class Contatto implements Comparable<Contatto>{

    private String nome;
    private String cognome;
    private List<String> telefono;
    private List<String> email;
    private Photo immagine;
    private Date dataCreazione;
    private String note;
    private boolean isPreferito;

    public Contatto(String nome, String cognome, List<String> telefono,
                    List<String> email, Photo immagine, Date dataCreazione,
                    String note, boolean isPreferito) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.immagine = immagine;
        this.dataCreazione = dataCreazione;
        this.note = note;
        this.isPreferito = isPreferito;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public List<String> getTelefono() {
        return this.telefono;
    }

    public List<String> getEmail() {
        return this.email;
    }

    public Photo getImmagine() {
        return this.immagine;
    }

    public Date getDataCreazione() {
        return this.dataCreazione;
    }

    public String getNote() {
        return this.note;
    }

    public boolean getIsPreferito() {
        return this.isPreferito;
    }

    public Contatto importaVCard(VCard vCard){
        return null;
    }

    public VCard esportaContatto(){
        return null;
    }

    @Override
    public int compareTo(Contatto c) {
        return 0;
    }
}
