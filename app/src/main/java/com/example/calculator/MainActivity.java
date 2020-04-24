package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    TextView operations = findViewById(R.id.operations);

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

    public void onClickSum(View view) {
        operations.append("+");
        Log.d("MainActivity", "The result is: " + (10 + 33));
    }


    //        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });


    //    AdSize adSize = new AdSize(300, 50);
//    private AdView mAdView;

//    mAdView.setAdListener(new AdListener() {
//        @Override
//        public void onAdLoaded() {
//            // Code to be executed when an ad finishes loading.
//        }
//
//        @Override
//        public void onAdFailedToLoad(int errorCode) {
//            // Code to be executed when an ad request fails.
//        }
//
//        @Override
//        public void onAdOpened() {
//            // Code to be executed when an ad opens an overlay that
//            // covers the screen.
//        }
//
//        @Override
//        public void onAdClicked() {
//            // Code to be executed when the user clicks on an ad.
//        }
//
//        @Override
//        public void onAdLeftApplication() {
//            // Code to be executed when the user has left the app.
//        }
//
//        @Override
//        public void onAdClosed() {
//            // Code to be executed when the user is about to return
//            // to the app after tapping on an ad.
//        }
//    });


}