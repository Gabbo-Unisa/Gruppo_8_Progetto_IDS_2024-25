package rubrica.Models;


import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Rubrica implements Serializable {
    Set<Contatto> contatti = new TreeSet<>();

    public void aggiungiContatto(Contatto c){

    }

    public void modificaContatto(Contatto old, Contatto update){

    }

    public void eliminaContatto(Contatto c){

    }

    public Set<Contatto> ricercaContatti(String query){
        return null;
    }

    public Set<Contatto> getContattiPreferiti(){
        return null;
    }

}
