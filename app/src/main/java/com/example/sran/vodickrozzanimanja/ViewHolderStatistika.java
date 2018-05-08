package com.example.sran.vodickrozzanimanja;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolderStatistika extends RecyclerView.ViewHolder {
    TextView naziv;
    TextView zaposleni;
    TextView mogucnostNastavka;
    TextView stanjeAktivnePonude;
    TextView nazivlabel;
    TextView zaposlenilabel;
    TextView mogucnostNastavkalabel;
    TextView stanjeAktivnePonudelabel;




    public ViewHolderStatistika(View itemView) {
        super(itemView);
        naziv = (TextView)itemView.findViewById(R.id.naziv_Zanimanja_id);
        zaposleni = (TextView)itemView.findViewById(R.id.zaposleni_id);
        mogucnostNastavka = (TextView)itemView.findViewById(R.id.mogucnost_nastavka_id);
        stanjeAktivnePonude = (TextView)itemView.findViewById(R.id.stanje_aktivne_ponude_id);
        nazivlabel  = (TextView)itemView.findViewById(R.id.naziv_Zanimanja_label_id);;
        zaposlenilabel = (TextView)itemView.findViewById(R.id.zaposleni_label_id);;
        mogucnostNastavkalabel  = (TextView)itemView.findViewById(R.id.mogucnost_nastavka_label_id);;
        stanjeAktivnePonudelabel = (TextView)itemView.findViewById(R.id.stanje_aktivne_ponude_label_id);;

    }
}
