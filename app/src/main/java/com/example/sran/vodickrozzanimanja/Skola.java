package com.example.sran.vodickrozzanimanja;

public class Skola
{
    String Naziv;
    String Zanimanja;
    String DetaljiUpisa;

    public Skola(String naziv, String zanimanja, String detaljiUpisa) {
        Naziv = naziv;
        Zanimanja = zanimanja;
        DetaljiUpisa = detaljiUpisa;
    }

    public String getNaziv() {
        return Naziv;
    }

    public String getZanimanja() {
        return Zanimanja;
    }

    public String getDetaljiUpisa() {
        return DetaljiUpisa;
    }
}

