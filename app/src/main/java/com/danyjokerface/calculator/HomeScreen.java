package com.danyjokerface.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Log.d(HomeScreen.class.getName(), "sono nell'onCreate!");
    }

//    public void onResume() {
//        super.onResume();
//        View author = findViewById(R.id.author);/
//        author.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // mettere condizione se si inserisce la mia data di nascita
//                Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
//                startActivity(intent);
//            }
//        });
//
//    }

    @SuppressLint("SetTextI18n")
    public void onClickAuthor(View view) {
        //TODO mettere condizione se si inserisce la mia data di nascita
//        Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
//        //intent.putExtra("Benvenuto", "Benvenuto nella prima activity di test!");
//        startActivity(intent);
        int count = 0;

        count++;
        TextView text = findViewById(R.id.author);
        text.setText("No.of Clicks " + count);

        if (count == 5) {
            Intent intent = new Intent(getApplicationContext(), FirstQuizEasterEgg.class);
            //intent.putExtra("Benvenuto", "Benvenuto nella prima activity di test!");
            startActivity(intent);
        }

    }

}