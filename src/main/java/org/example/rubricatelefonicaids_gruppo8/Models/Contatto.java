package org.example.rubricatelefonicaids_gruppo8.Models;

import ezvcard.VCard;
import ezvcard.property.Photo;

import java.time.LocalDate;
import java.util.List;

public class Contatto implements Comparable{

    private String nome;
    private String cognome;
    private List<String> telefono;/*array di String[] o lista?*/
    private List<String> email;/*array di String[] o lista?*/
    private Photo immagine;
    private LocalDate dataCreazione;
    private String note;
    private boolean isPreferito;

    public Contatto(String nome, String cognome, List<String> telefono, List<String> email, Photo immagine, LocalDate dataCreazione, String note, boolean isPreferito) {
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
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public List<String> getTelefono() {
        return telefono;
    }

    public List<String> getEmail() {
        return email;
    }

    public Photo getImmagine() {
        return immagine;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public String getNote() {
        return note;
    }

    public boolean isPreferito() {
        return isPreferito;
    }

    public VCard importaContatto(){
        return null;
    }

    public VCard esportaContatto(){
        return null;
    }

    @Override
    public int compareTo(Object o /*Contatto c*/) {
        /*String questa = this.getNome() + this.getCognome();
        String daConfrontare = o.getNome() + o.getCognome();*/
        return this.nome.compareTo((String) o/*.getNome()*/);
    }
}
