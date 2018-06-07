package com.sport.bein;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

public class ServersActivity extends AppCompatActivity {
    TextView tvS1,tvS2,tvS3;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servers_activity);

        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admobInterstital).toString());
        AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest1);
        adView.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


        tvS1 = (TextView)findViewById(R.id.s1);
        tvS2 = (TextView)findViewById(R.id.s2);
        tvS3 = (TextView)findViewById(R.id.s3);

        tvS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServersActivity.this,ChannelsActivity.class));
                Toast.makeText(ServersActivity.this, getString(R.string.s1).toString(), Toast.LENGTH_SHORT).show();
                interstitialAd.show();
            }
        });

        tvS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServersActivity.this,ChannelsActivity.class));
                Toast.makeText(ServersActivity.this, getString(R.string.s2).toString(), Toast.LENGTH_SHORT).show();
                interstitialAd.show();
            }
        });

        tvS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServersActivity.this,ChannelsActivity.class));
                Toast.makeText(ServersActivity.this, getString(R.string.s3).toString(), Toast.LENGTH_SHORT).show();
                interstitialAd.show();
            }
        });
    }
}
