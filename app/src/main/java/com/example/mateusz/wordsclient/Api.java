package com.example.mateusz.wordsclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.1.12:10100/";
    @GET("words")
    Call<Ids>getID();


}
