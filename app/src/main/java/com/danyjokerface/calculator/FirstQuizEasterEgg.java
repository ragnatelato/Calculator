package com.danyjokerface.calculator;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class FirstQuizEasterEgg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_quiz_easter_egg);
    }

    @Override
    protected void onResume() {
        //TODO mettere condizione se si inserisce la mia data di nascita
        super.onResume();
        //noinspection ConstantConditions
        Log.d("Benvenuto", getIntent().getExtras().getString("Benvenuto"));
    }
}