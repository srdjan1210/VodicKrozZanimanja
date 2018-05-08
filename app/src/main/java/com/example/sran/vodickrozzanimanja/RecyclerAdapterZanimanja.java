package com.example.sran.vodickrozzanimanja;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapterZanimanja extends RecyclerView.Adapter<ViewHolderZanimanja> {
    ArrayList<TabelaObjekat> tabelaObjekata;


    public RecyclerAdapterZanimanja(ArrayList<TabelaObjekat> tabelaObjekata) {
        this.tabelaObjekata = tabelaObjekata;
    }

    @NonNull
    @Override
    public ViewHolderZanimanja onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_detaljizanimanja, parent, false);
        return new ViewHolderZanimanja(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderZanimanja holder, int position) {
        TabelaObjekat dz = tabelaObjekata.get(position);

        holder.nazivLabel.setText("Назив");
        holder.stepenLabel.setText("Степен");
        holder.brojUcenikaLabel.setText("Ученика");
        holder.brojOdjeljenjaLabel.setText("Одјељења");

        holder.naziv.setText(dz.getNaziv());
        holder.stepen.setText(dz.getStepen());
        holder.brojUcenika.setText(dz.getBrojUcenika());
        holder.brojOdjeljenja.setText(dz.getBrojOdjeljenja());


    }

    @Override
    public int getItemCount() {
        return tabelaObjekata.size();
    }
}
