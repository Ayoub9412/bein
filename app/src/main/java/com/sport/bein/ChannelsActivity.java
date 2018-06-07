package com.sport.bein;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class ChannelsActivity extends AppCompatActivity {

    ListView myList;
    ArrayList<Channel> channelsList = new ArrayList<>();
    ChannlesAdapter myAdapter;
    TextView tvName;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channels_activity);

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

        channelsList.add(new Channel("Bein Max 2",R.drawable.beinmax2));
        channelsList.add(new Channel("Bein Max 3",R.drawable.beinmax3));
        channelsList.add(new Channel("Bein Max 1",R.drawable.beinmax1));
        channelsList.add(new Channel("Bein Max 2",R.drawable.beinmax2));
        channelsList.add(new Channel("Bein Max 3",R.drawable.beinmax3));
        channelsList.add(new Channel("Bein Max 1",R.drawable.beinmax1));
        channelsList.add(new Channel("Bein Max 2",R.drawable.beinmax2));
        channelsList.add(new Channel("Bein Max 3",R.drawable.beinmax3));
        channelsList.add(new Channel("Bein Max 1",R.drawable.beinmax1));
        channelsList.add(new Channel("Bein Max 2",R.drawable.beinmax2));
        channelsList.add(new Channel("Bein Max 3",R.drawable.beinmax3));
        channelsList.add(new Channel("Bein Max 1",R.drawable.beinmax1));

        myAdapter = new ChannlesAdapter(this,channelsList);

        myList = (ListView)findViewById(R.id.myList);

        myList.setAdapter(myAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvName = view.findViewById(R.id.channelName);
                Toast.makeText(ChannelsActivity.this, tvName.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ChannelsActivity.this,LastActivity.class));
                interstitialAd.show();
            }
        });

    }
}
