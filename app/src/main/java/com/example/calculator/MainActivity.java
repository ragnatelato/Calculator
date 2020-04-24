package com.example.calculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImmersive(true);

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

}