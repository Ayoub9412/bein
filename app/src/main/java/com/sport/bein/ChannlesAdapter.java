package com.sport.bein;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChannlesAdapter extends ArrayAdapter<Channel> {

    View channelView;
    TextView tvName;
    ImageView chImage;
    Channel currentChannel;


    public ChannlesAdapter(@NonNull Context context, @NonNull List<Channel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        channelView = convertView;
        if (channelView==null){
            channelView = LayoutInflater.from(getContext()).inflate(R.layout.chain_item,null);
        }
        currentChannel = getItem(position);
        tvName = (TextView)channelView.findViewById(R.id.channelName);
        chImage = (ImageView)channelView.findViewById(R.id.channelImage);

        tvName.setText(currentChannel.getChName());
        chImage.setImageResource(currentChannel.getChImage());


        return channelView;
    }
}
