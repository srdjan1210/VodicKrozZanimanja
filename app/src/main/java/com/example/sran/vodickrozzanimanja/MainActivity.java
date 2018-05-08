package com.example.sran.vodickrozzanimanja;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    List<Opstina> gradovi;
    ViewPager viewPager;
    ViewPagerAdapter mViewPagerAdapter;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    int[] slike ={
            R.drawable.banjaluka,R.drawable.berkovici,R.drawable.bijeljina,
            R.drawable.bileca,R.drawable.bratunac,R.drawable.brod,
            R.drawable.visegrad,R.drawable.vlasenica,R.drawable.vukosavlje,
            R.drawable.gacko,R.drawable.gradiska,R.drawable.derventa,
            R.drawable.doboj,R.drawable.donjizabar,R.drawable.zvornik,
            R.drawable.istocnailidza,R.drawable.istocnidrvar,R.drawable.istocnistarigrad,
            R.drawable.istocnonovosarajevo,R.drawable.janja,R.drawable.jezero,
            R.drawable.kalinovik,R.drawable.knezevo,R.drawable.kozarskadubica,
            R.drawable.kostajnica,R.drawable.kotorvaros,R.drawable.krupanauni,
            R.drawable.kupres,R.drawable.laktasi,R.drawable.lopare,
            R.drawable.ljubinje,R.drawable.milici,R.drawable.modrica,
            R.drawable.mrkonjicgrad,R.drawable.nevesinje,R.drawable.novigrad,
            R.drawable.novogorazde,R.drawable.osmaci,R.drawable.ostraluka,
            R.drawable.pale,R.drawable.pelagicevo,R.drawable.petrovacdrinic,
            R.drawable.petrovo,R.drawable.prijedor,R.drawable.prnjavor,
            R.drawable.ribnik,R.drawable.rogatica,R.drawable.rudo,
            R.drawable.sokolac,R.drawable.srbac,R.drawable.srebrenica,
            R.drawable.teslic,R.drawable.trebinje,R.drawable.trnovo,
            R.drawable.ugljevik,R.drawable.foca,R.drawable.hanpijesak,
            R.drawable.cajnice, R.drawable.celinac,R.drawable.samac,R.drawable.sekovici,R.drawable.sipovo
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Општине");


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Учитавање општина!");
        progressDialog.setMessage("Молимо сачекајте...");
        progressDialog.show();


        viewPager = (ViewPager)findViewById(R.id.viewPager_id);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        getOpstineFromApi(api);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSubtitle(gradovi.get(position).getNaziv());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void getOpstineFromApi(final Api api)
    {
        Call<List<Opstina>> call2= api.getOpstina();
        call2.enqueue(new Callback<List<Opstina>>() {
            @Override
            public void onResponse(Call<List<Opstina>> call, Response<List<Opstina>> response) {
                List<Opstina> opstine = response.body();
                setViewPagerAdapter(opstine);
                progressDialog.cancel();
            }
            @Override
            public void onFailure(Call<List<Opstina>> call, Throwable t) {
                progressDialog.setMessage("Лоша интернет конекција!");
            }
        });

    }

    public void setViewPagerAdapter(List<Opstina> opstine)
    {
        gradovi = opstine;
        int index = 0;
        getSupportActionBar().setSubtitle(gradovi.get(0).getNaziv());

        for(Opstina o: gradovi){
            Bundle b = new Bundle();
            b.putInt("idSlike",slike[index]);
            index++;

            Fragment f = new Fragment();
            f.setArguments(b);
            mViewPagerAdapter.addFragment(f,o.getNaziv());
        }
        viewPager.setAdapter(mViewPagerAdapter);

    }

    public void pogledajSkole(View v)
    {
        Bundle paket = new Bundle();
        paket.putString("imeSkole",viewPager.getAdapter().getPageTitle(viewPager.getCurrentItem()).toString());

        Intent zanimanja = new Intent(MainActivity.this,ZanimanjaActivity.class);
        zanimanja.putExtras(paket);
        startActivity(zanimanja);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        MenuItem menuItem = menu.findItem(R.id.search_id);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int index = 0;
                for(Opstina o:gradovi){
                    if(o.getNaziv().toLowerCase().contains(query.toLowerCase())){
                        viewPager.setCurrentItem(index);
                        return true;
                    }
                    index++;
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.search_id){
            item.expandActionView();
        }
        return super.onOptionsItemSelected(item);
    }



}
