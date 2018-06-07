package com.sport.bein;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class LastActivity extends AppCompatActivity {

    ProgressBar myProgressBar;
    TextView tvDialog;
    Button btnRate;
    View dialogView;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    InterstitialAd interstitialAd;
    int rateClicked=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

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

        final Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName().toString()));

        dialogView = getLayoutInflater().inflate(R.layout.loading_view,null,false);

        btnRate = (Button)dialogView.findViewById(R.id.btnRate);
        myProgressBar = (ProgressBar)dialogView.findViewById(R.id.myPb);
        tvDialog = (TextView)dialogView.findViewById(R.id.tvDialog);

        builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        btnRate.setVisibility(View.GONE);


        Handler h = new Handler();
        final Handler h2= new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
                h2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvDialog.setText(getString(R.string.RateMessage).toString());
                        myProgressBar.setVisibility(View.GONE);
                        btnRate.setVisibility(View.VISIBLE);
                    }
                },4000);
            }
        },2000);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rateClicked==0){
                    interstitialAd.show();
                    rateClicked++;
                }
                else{
                    Toast.makeText(LastActivity.this, "5 نجوم", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }


            }
        });

    }
}
