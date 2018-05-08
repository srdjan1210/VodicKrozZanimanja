package com.example.sran.vodickrozzanimanja;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.TextView;

public class ViewHolderOpis extends RecyclerView.ViewHolder {
    TextView nazivLabel;
    TextView strukaLabel;
    TextView razrediLabel;
    TextView opisLabel;
    TextView naziv;
    TextView struka;
    TextView razredi;
    TextView opis;




    public ViewHolderOpis(View itemView) {
        super(itemView);
        nazivLabel = (TextView)itemView.findViewById(R.id.naziv_label_opis_id);
        strukaLabel = (TextView)itemView.findViewById(R.id.struka_label_opis_id);
        razrediLabel = (TextView)itemView.findViewById(R.id.razredi_label_opis_id);
        opisLabel = (TextView)itemView.findViewById(R.id.opis_label_id);

        naziv = (TextView)itemView.findViewById(R.id.naziv_opis_id);
        struka = (TextView)itemView.findViewById(R.id.struka_opis_id);
        razredi = (TextView)itemView.findViewById(R.id.razredi_opis_id);
        opis = (TextView)itemView.findViewById(R.id.opis_opis_id);



    }
}
