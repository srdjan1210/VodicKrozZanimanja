package com.example.sran.vodickrozzanimanja;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<Skola> skole;
    Context context;


    public RecyclerAdapter(List<Skola> skole, Context context) {
        this.skole = skole;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycleritem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skola test = skole.get(position);
        holder.imeSkole.setText(test.getNaziv());
        holder.button.setText("Погледај смјерове");
        holder.button.setTextColor(R.color.white);

    }

    @Override
    public int getItemCount() {
        return skole.size();
    }






}
