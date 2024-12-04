package org.example.rubricatelefonicaids_gruppo8.Models;

import java.util.LinkedList;
import java.util.List;

public class Rubrica {
    List<Contatto> contatti = new LinkedList<>();

    public void aggiungiContatto(){

    }

    public void modificaContatto(Contatto daSostituire,Contatto aggiornato){

    }

    public void eliminaContatto(Contatto c){

    }

    public List<Contatto> ricercaContatti(){
        return null;
    }

    public List<Contatto> getContattiPreferiti(){
        List<Contatto> preferiti= new LinkedList<>();
        preferiti=null;

        for(Contatto c: contatti){
            if(c.isPreferito() == true){
                preferiti.add(c);
            }
        }

        /*if(preferiti.size() > 0) {
            return preferiti;
        }else{
            return null;
        }*/
        return preferiti;
    }
}
