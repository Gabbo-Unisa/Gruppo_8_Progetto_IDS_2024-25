package org.example.rubricatelefonicaids_gruppo8.Models;

import com.sun.source.tree.Tree;
import ezvcard.VCard;
import ezvcard.property.Photo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Contatto implements Comparable<Contatto>{

    private String nome;
    private String cognome;
    private List<String> telefono;
    private List<String> email;
    private Photo immagine;
    private LocalDate dataCreazione;
    private String note;
    private boolean isPreferito;

    public Contatto(String nome, String cognome, List<String> telefono,
                    List<String> email, Photo immagine, LocalDate dataCreazione,
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
    public int compareTo(Contatto c) {
        return 0;
    }
}
