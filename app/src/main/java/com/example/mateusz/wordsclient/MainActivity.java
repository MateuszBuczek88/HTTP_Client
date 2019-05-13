package com.example.mateusz.wordsclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Ids> call = api.getID();

        call.enqueue(new Callback<Ids>() {
            @Override
            public void onResponse(Call<Ids> call, Response<Ids> response) {
                List <Integer> ids = response.body().getIds();
                for (Integer a : ids) {
                    Log.d("ID",a.toString());
                }

            }

            @Override
            public void onFailure(Call<Ids> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void startWords(View view) {

        Intent intent = new Intent(this, WordActivity.class);
        startActivity(intent);

    }
}
