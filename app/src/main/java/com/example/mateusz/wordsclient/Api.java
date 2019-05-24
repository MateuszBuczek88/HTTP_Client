package com.example.mateusz.wordsclient;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.1.12:9980/";
    @GET("words")
    Call<WordsIds> getID();
}
