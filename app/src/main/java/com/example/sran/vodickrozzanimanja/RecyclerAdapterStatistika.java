package com.example.sran.vodickrozzanimanja;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapterStatistika extends RecyclerView.Adapter<ViewHolderStatistika> {
    ArrayList<DetaljiZanimanja> detaljiZanimanja;
    public RecyclerAdapterStatistika(ArrayList<DetaljiZanimanja> detaljiZanimanjas) {
        detaljiZanimanja = detaljiZanimanjas;
    }

    @NonNull
    @Override
    public ViewHolderStatistika onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_statistika, parent, false);
        return new ViewHolderStatistika(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStatistika holder, int position) {
        DetaljiZanimanja dz = detaljiZanimanja.get(position);

        holder.naziv.setText(dz.getNaziv());
        if(dz.getMogucnostNastavka()){
            holder.mogucnostNastavka.setText("Da");
        }else{
            holder.mogucnostNastavka.setText("Ne");
        }
        if(dz.getStanjeAktivnePonudeNaDan31122017Godine().trim().equals("")){
            holder.stanjeAktivnePonude.setText("Nema podataka!");
        }else {
            holder.stanjeAktivnePonude.setText(dz.getStanjeAktivnePonudeNaDan31122017Godine());
        }
        if(dz.getZaposleniU2017godini().trim().equals("")){
            holder.zaposleni.setText("Nema podataka!");
        }else {
            holder.zaposleni.setText(dz.getZaposleniU2017godini());
        }


        holder.nazivlabel.setText("Назив:");
        holder.zaposlenilabel.setText("Запослени у 2017 години:");
        holder.mogucnostNastavkalabel.setText("Могућност наставка:");
        holder.stanjeAktivnePonudelabel.setText("Стање активне понуде:");
    }

    @Override
    public int getItemCount() {
        return detaljiZanimanja.size();
    }
}
