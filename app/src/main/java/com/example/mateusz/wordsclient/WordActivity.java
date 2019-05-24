package com.example.mateusz.wordsclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import java.util.ArrayList;
import java.util.logging.Logger;

public class WordActivity extends AppCompatActivity {

    Word word;
    ArrayList<Integer> idslist;
    Button buttonYes;
    Button buttonNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent = getIntent();
        idslist = intent.getIntegerArrayListExtra("IdsList");
        buttonNo = findViewById(R.id.buttonNo);
        buttonYes = findViewById(R.id.buttonYes);
        buttonYes.setText(idslist.get(0).toString());
        Log.d("mbuczek", idslist.toString());

    }
}

