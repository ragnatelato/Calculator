package com.example.calculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.danyjokerface.calculator.R;


public class MainActivity extends AppCompatActivity {

//    TextView operations = findViewById(R.id.operations);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        button = findViewById(R.id.button_comma);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("MainActivity", "Ciao Dany!");
//            }
//        });
    }

//    public void onClickSum(View view) {
//        operations.append("+");
//        Log.d("MainActivity", "The result is: " + (10 + 33));
//    }

}