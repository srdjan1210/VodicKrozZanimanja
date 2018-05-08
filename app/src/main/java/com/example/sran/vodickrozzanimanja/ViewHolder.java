package com.example.sran.vodickrozzanimanja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHolder extends RecyclerView.ViewHolder
{
    public TextView imeSkole;
    public AppCompatButton button;

    public ViewHolder(View itemView) {
        super(itemView);
        imeSkole = (TextView) itemView.findViewById(R.id.imeSkole_id);
        button = (AppCompatButton)itemView.findViewById(R.id.appCompatButton_recyclerView);

    }
}
