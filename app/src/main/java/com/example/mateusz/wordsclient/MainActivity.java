package com.example.mateusz.wordsclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ProgressBar progressBar;
    List<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        button = findViewById(R.id.startButton);
        progressBar = findViewById(R.id.progress_circular);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWords(v);
            }
        });

        Api api = retrofit.create(Api.class);
        Call<WordsIds> call = api.getID();
/*
        call.enqueue(new Callback<WordsIds>() {
            @Override
            public void onResponse(Call<WordsIds> call, Response<WordsIds> response) {
                List<Integer> ids = response.body().getIds();
            }

            @Override
            public void onFailure(Call<WordsIds> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        */
       new FetchData(call).execute();

    }

    public void startWords(View view) {
        Intent intent = new Intent(this, WordActivity.class);
        startActivity(intent);
    }

    class FetchData extends AsyncTask<Void, Void, Response<WordsIds>> {

        public FetchData (Call call) {
            this.call = call;
        }

        private Call call;

        @Override
        protected Response<WordsIds> doInBackground(Void... voids) {
            Response<WordsIds> result = null;
            try {
                result = call.execute();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;


        }

        @Override
        protected void onPostExecute(Response<WordsIds> result) {
            if (result != null) {
                ids = result.body().getIds();
            } else {

            }
            progressBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            button.setText(ids.get(2).toString());
        }
    }
}


