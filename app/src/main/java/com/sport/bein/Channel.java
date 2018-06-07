package com.sport.bein;

public class Channel {
    String chName;
    int chImage;

    public Channel(String Name, int Image){
        chName = Name;
        chImage = Image;
    }

    public String getChName() {
        return chName;
    }

    public int getChImage() {
        return chImage;
    }

}
