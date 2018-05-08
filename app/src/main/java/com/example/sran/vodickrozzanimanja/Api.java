package com.example.sran.vodickrozzanimanja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String baseUrl = "http://www.eobrazovanje.com/srednje_obrazovanje/_vti_bin/MobileAppServices/VodicZanimanja.svc/";
    @GET("Opstine")
    Call<List<Opstina>> getOpstina();

    @GET("Skole")
    Call<List<Skola>> getSkole(@Query("opstina") String grad);

    @GET("ZanimanjeDetalji")
    Call<DetaljiZanimanja> getDetaljiZanimanja(@Query("Zanimanje") String zanimanje);




}
