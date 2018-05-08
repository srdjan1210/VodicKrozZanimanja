package com.example.sran.vodickrozzanimanja;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetaljiZanimanjaActivity extends AppCompatActivity {


    int pozicija;
    int index = 0;
    Api api;
    Skola skola;
    DetaljiZanimanja detaljiZanimanja;

    ArrayList<TabelaObjekat> tabelaObjekata;
    ArrayList<DetaljiZanimanja> mdetaljiZanimanja;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Toolbar toolbar;;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_zanimanja);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Учитавање занимања!");
        progressDialog.setMessage("Молимо сачекајте...");
        progressDialog.show();

        toolbar = (Toolbar)findViewById(R.id.toolbarDetaljiZanimanja);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Занимања");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewZanimanja_id);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        pozicija = b.getInt("index");
        String grad = getFromSharedPrefs();

        mdetaljiZanimanja = new ArrayList<>();
        tabelaObjekata = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        getSkoleFromApi(api,grad,this);

    }

    private String getFromSharedPrefs()
    {
        String grad;
        SharedPreferences sp = getSharedPreferences("grad",MODE_PRIVATE);
        grad = sp.getString("grad","nemaGrada");
        return grad;
    }

    private void getSkoleFromApi(final Api api, String nazivGrada,final Context con)
    {
        Call<List<Skola>> call2 = api.getSkole(nazivGrada);
        call2.enqueue(new Callback<List<Skola>>() {
            @Override
            public void onResponse(Call<List<Skola>> call, Response<List<Skola>> response) {
                List<Skola> skole = response.body();
                skola = skole.get(pozicija);
                getDataFromHtml();
            }
            @Override
            public void onFailure(Call<List<Skola>> call, Throwable t) {t.printStackTrace();}
        });
    }

    private void getDetaljiZanimanjaFromApi(String smjer)
    {

        Call<DetaljiZanimanja> call = api.getDetaljiZanimanja(smjer);
        call.enqueue(new Callback<DetaljiZanimanja>() {
            @Override
            public void onResponse(Call<DetaljiZanimanja> call, Response<DetaljiZanimanja> response) {
                DetaljiZanimanja dz = response.body();
                addDetaljiZanimanjaToList(dz);
                index++;
                if(index < tabelaObjekata.size()){
                    getDetaljiZanimanjaFromApi(tabelaObjekata.get(index).naziv);
                }else{
                    progressDialog.cancel();
                    setRecyclerAdapter();
                }
            }

            @Override
            public void onFailure(Call<DetaljiZanimanja> call, Throwable t) {

            }
        });
    }

    private void getDataFromHtml()
    {
        String html = skola.getDetaljiUpisa();
        Document doc = Jsoup.parse(html);
        Elements el = doc.select(determineSelector(html));

        for(int i = 0; i < el.size();i++ ){

            if((i+1) % 5 == 0){
                TabelaObjekat to = new TabelaObjekat(el.get(i-3).text(),el.get(i-2).text(),el.get(i-1).text(),el.get(i).text());
                tabelaObjekata.add(to);
            }
        }
        getDetaljiZanimanjaFromApi(tabelaObjekata.get(index).naziv);
    }

    @NonNull
    private String determineSelector(String html)
    {
        if (countStringOccurances(html, "<table") != 2) {
            return "td";
        }
        return "table:last-child td";
    }

    private int countStringOccurances(String html, String substring)
    {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {
            lastIndex = html.indexOf(substring, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += substring.length();
            }
        }

        return count;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_statistika,menu);
        menu.getItem(0).setTitle("Опис");
        menu.getItem(1).setTitle("Одјељења");
        menu.getItem(2).setTitle("Статистика");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }else if(item.getItemId() == R.id.statisticki_podaci_id){
            recyclerView.setAdapter(new RecyclerAdapterStatistika(mdetaljiZanimanja));
        }else if(item.getItemId() == R.id.item_odjeljenja_id){
            recyclerView.setAdapter(new RecyclerAdapterZanimanja(tabelaObjekata));
        }else if(item.getItemId() == R.id.item_opis_id){
            recyclerView.setAdapter(new RecyclerAdapterOpis(mdetaljiZanimanja));
        }

        return super.onOptionsItemSelected(item);
    }

    public void addDetaljiZanimanjaToList(DetaljiZanimanja detaljiZanimanja)
    {
        if(detaljiZanimanja != null)
        mdetaljiZanimanja.add(detaljiZanimanja);
    }

    public void setRecyclerAdapter()
    {
        recyclerView.setAdapter(new RecyclerAdapterOpis(mdetaljiZanimanja));
    }
}
