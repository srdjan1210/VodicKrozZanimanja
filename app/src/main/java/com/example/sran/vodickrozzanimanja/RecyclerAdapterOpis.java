package com.example.sran.vodickrozzanimanja;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerAdapterOpis extends RecyclerView.Adapter<ViewHolderOpis> {
    ArrayList<DetaljiZanimanja> detaljiZanimanja;

    public RecyclerAdapterOpis(ArrayList<DetaljiZanimanja> detaljiZanimanja)
    {
        this.detaljiZanimanja = detaljiZanimanja;
    }

    @NonNull
    @Override
    public ViewHolderOpis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_opis, parent, false);
        return new ViewHolderOpis(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOpis holder, int position) {
        DetaljiZanimanja dz = detaljiZanimanja.get(position);

        holder.nazivLabel.setText("Назив:");
        holder.strukaLabel.setText("Струка:");
        holder.razrediLabel.setText("Разреди:");
        holder.opisLabel.setText("Опис:");

        holder.naziv.setText(dz.getNaziv());
        holder.struka.setText(dz.getStruka());
        holder.razredi.setText(dz.getStepenSlozenosti()+"");
        holder.opis.setText(dz.getOpis());
    }

    @Override
    public int getItemCount() {
        return detaljiZanimanja.size();
    }
}
