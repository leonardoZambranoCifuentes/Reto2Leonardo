package com.example.reto2_leonardo;

import com.deezer.sdk.network.connect.DeezerConnect;

import androidx.appcompat.app.AppCompatActivity;

public class DeezerConnection {

    public final static String APPID = "411122";
    private DeezerConnect deezerConnect;


    public DeezerConnection(){
            deezerConnect = new DeezerConnect(APPID);

    }


    public DeezerConnect getDeezerConnect() {
        return deezerConnect;
    }

    public void setDeezerConnect(DeezerConnect deezerConnect) {
        this.deezerConnect = deezerConnect;
    }
}
