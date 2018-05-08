package com.example.sran.vodickrozzanimanja;

import com.google.gson.annotations.SerializedName;

public class DetaljiZanimanja
{
    @SerializedName("Naziv")
    String Naziv;
    @SerializedName("StepenSlozenosti")
    Integer StepenSlozenosti;
    @SerializedName("Opis")
    String Opis;
    @SerializedName("MogucnostNastavka")
    Boolean MogucnostNastavka;
    @SerializedName("ZaposleniU2017godini")
    String ZaposleniU2017godini;
    @SerializedName("StanjeAktivnePonudeNaDan31122017Godine")
    String StanjeAktivnePonudeNaDan31122017Godine;
    @SerializedName("Struka")
    String Struka;



    public String getNaziv() { return Naziv; }

    public Integer getStepenSlozenosti() {
        return StepenSlozenosti;
    }

    public String getOpis() {
        return Opis;
    }

    public Boolean getMogucnostNastavka() {
        return MogucnostNastavka;
    }

    public String getZaposleniU2017godini() {
        return ZaposleniU2017godini;
    }

    public String getStanjeAktivnePonudeNaDan31122017Godine() { return StanjeAktivnePonudeNaDan31122017Godine; }

    public String getStruka() {
        return Struka;
    }

}

