package com.example.sran.vodickrozzanimanja;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZanimanjaActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ProgressDialog progressDialog;
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zanimanja);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Све школе");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String ime = b.getString("imeSkole");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Учитавање школа!");
        progressDialog.setMessage("Молимо сачекајте...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        putInSharedPrefs(ime);
        getSkoleFromApi(api,ime,this);


    }

    public void getSkoleFromApi(final Api api, String nazivGrada,final Context con)
    {

        Call<List<Skola>> call2 = api.getSkole(nazivGrada);
        call2.enqueue(new Callback<List<Skola>>() {
            @Override
            public void onResponse(Call<List<Skola>> call, Response<List<Skola>> response) {
                List<Skola> skole = response.body();
                if(skole.size() != 0) {
                    recyclerView.setAdapter(new RecyclerAdapter(skole,con));
                    progressDialog.cancel();
                } else {
                    progressDialog.cancel();
                    Toast.makeText(ZanimanjaActivity.this, "Ова општина нема школа за приказ!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Skola>> call, Throwable t) {
                Toast.makeText(ZanimanjaActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void putInSharedPrefs(String s){
        SharedPreferences.Editor sp = getSharedPreferences("grad",MODE_PRIVATE).edit();
        sp.putString("grad",s);
        sp.apply();
        sp.commit();
    }

    public void pogledajSmjerove(View v){
        ViewHolder view = (ViewHolder)recyclerView.findContainingViewHolder(v);
        Bundle b = new Bundle();
        b.putInt("index",view.getLayoutPosition());

        Intent i = new Intent(ZanimanjaActivity.this,DetaljiZanimanjaActivity.class);
        i.putExtras(b);
        startActivity(i);
    }









}
