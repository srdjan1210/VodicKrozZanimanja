package com.example.sran.vodickrozzanimanja;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolderZanimanja extends RecyclerView.ViewHolder {

    public TextView naziv;
    public TextView brojOdjeljenja;
    public TextView brojUcenika;
    public TextView stepen;
    public TextView nazivLabel;
    public TextView stepenLabel;
    public TextView brojUcenikaLabel;
    public TextView brojOdjeljenjaLabel;

    public ViewHolderZanimanja(View itemView) {
        super(itemView);

        naziv = (TextView)itemView.findViewById(R.id.naziv_id);
        stepen = (TextView)itemView.findViewById(R.id.stepen_id);
        brojUcenika = (TextView)itemView.findViewById(R.id.brojUcenika_id);
        brojOdjeljenja = (TextView)itemView.findViewById(R.id.brojOdjeljenja_id);

        nazivLabel = (TextView)itemView.findViewById(R.id.naziv_label_id);
        stepenLabel = (TextView)itemView.findViewById(R.id.stepen_label_id);
        brojUcenikaLabel = (TextView)itemView.findViewById(R.id.brojUcenika_label_id);
        brojOdjeljenjaLabel = (TextView)itemView.findViewById(R.id.brojOdjeljenja_label_id);
    }
}
