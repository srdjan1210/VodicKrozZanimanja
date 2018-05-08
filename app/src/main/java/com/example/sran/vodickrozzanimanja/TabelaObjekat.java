package com.example.sran.vodickrozzanimanja;

public class TabelaObjekat {

    String naziv;
    String stepen;
    String brojUcenika;
    String brojOdjeljenja;

    public TabelaObjekat(String naziv, String stepen, String brojUcenika, String brojOdjeljenja) {
        this.naziv = naziv;
        this.stepen = stepen;
        this.brojUcenika = brojUcenika;
        this.brojOdjeljenja = brojOdjeljenja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getStepen() {
        return stepen;
    }

    public void setStepen(String stepen) {
        this.stepen = stepen;
    }

    public String getBrojUcenika() {
        return brojUcenika;
    }

    public void setBrojUcenika(String brojUcenika) {
        this.brojUcenika = brojUcenika;
    }

    public String getBrojOdjeljenja() {
        return brojOdjeljenja;
    }

    public void setBrojOdjeljenja(String brojOdjeljenja) {
        this.brojOdjeljenja = brojOdjeljenja;
    }






}
