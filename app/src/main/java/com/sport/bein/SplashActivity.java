package com.sport.bein;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.MobileAds;

import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MobileAds.initialize(this, getString(R.string.admobid).toString());

        final Intent i = new Intent(SplashActivity.this, MainActivity.class);

        img = (ImageView) findViewById(R.id.img);


        ((TransitionDrawable) img.getDrawable()).startTransition(3000);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();

                }
            }
        };
        thread.start();

    }
}
