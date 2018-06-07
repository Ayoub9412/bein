package com.sport.bein;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnNext;
    InterstitialAd interstitialAd;
    URL privacyUrl = null;
    ConsentForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = (Button)findViewById(R.id.btnNext);
        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.admobInterstital).toString());
        AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest1);

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        adView.loadAd(adRequest);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServersActivity.class));
                interstitialAd.show();

            }
        });


        //------------------------------------------------------------------//
        final ConsentInformation consentInformation = ConsentInformation.getInstance(getApplicationContext());
        String[] pubId = {getString(R.string.admobPublisherId).toString()};
        consentInformation.requestConsentInfoUpdate(pubId, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {

                Toast.makeText(MainActivity.this, consentStatus.toString(), Toast.LENGTH_SHORT).show();

                if (ConsentInformation.getInstance(getApplicationContext()).isRequestLocationInEeaOrUnknown()) {
                    if (consentStatus == ConsentStatus.PERSONALIZED) {
                        //Request Normal Ads
                        Toast.makeText(MainActivity.this, "NON_PERSONALIZED", Toast.LENGTH_SHORT).show();
                    } else if (consentStatus == ConsentStatus.NON_PERSONALIZED) {

                        Toast.makeText(MainActivity.this, "NON_PERSONALIZED", Toast.LENGTH_SHORT).show();

                    } else if (consentStatus == ConsentStatus.UNKNOWN) {

                    }

                    try {
                        privacyUrl = new URL("https://www.google.com");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        // Handle error.
                    }

                    form = new ConsentForm.Builder(getApplicationContext(), privacyUrl)
                            .withListener(new ConsentFormListener() {
                                @Override
                                public void onConsentFormLoaded() {
                                    // Consent form loaded successfully.
                                    form.show();
                                }

                                @Override
                                public void onConsentFormOpened() {
                                    // Consent form was displayed.
                                }

                                @Override
                                public void onConsentFormClosed(
                                        ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                                    // Consent form was closed.

                                }

                                @Override
                                public void onConsentFormError(String errorDescription) {
                                    // Consent form error.
                                }
                            })
                            .withPersonalizedAdsOption()
                            .withNonPersonalizedAdsOption()
                            .withAdFreeOption()
                            .build();
                    form.load();

                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String reason) {

            }
        });
    }
}
