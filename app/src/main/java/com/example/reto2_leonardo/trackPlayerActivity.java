package com.example.reto2_leonardo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.deezer.sdk.model.Permissions;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.event.DialogListener;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.DeezerError;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;
import com.deezer.sdk.player.TrackPlayer;
import com.deezer.sdk.player.exception.TooManyPlayersExceptions;
import com.deezer.sdk.player.networkcheck.NetworkStateChecker;
import com.deezer.sdk.player.networkcheck.NetworkStateListener;
import com.deezer.sdk.player.networkcheck.WifiAndMobileNetworkStateChecker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class trackPlayerActivity extends AppCompatActivity {


    private ImageView largeImageTrack;
    private TextView nameTrack;
    private TextView artistTrack;
    private TextView albumName;
    private TextView duration;
    private Button but_listen;
    private DeezerConnection deezerConnection;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_player);

        deezerConnection = new DeezerConnection();
        largeImageTrack = findViewById(R.id.trackImageLarge);
        nameTrack = findViewById(R.id.nameTrackPlayer);
        artistTrack = findViewById(R.id.artistTrackPlayer);
        albumName = findViewById(R.id.albumName);
        duration = findViewById(R.id.duration);
        but_listen = findViewById(R.id.but_listen);

        Intent i = getIntent();
        final long message = (long) i.getExtras().get("ID Track");

        String[] permissions = new String[] {
                Permissions.BASIC_ACCESS,
                Permissions.MANAGE_LIBRARY,
                Permissions.LISTENING_HISTORY };

        DialogListener listener = new DialogListener() {

            public void onComplete(Bundle values) {}

            public void onCancel() {}

            public void onException(Exception e) {}
        };

        deezerConnection.getDeezerConnect().authorize(this, permissions, listener);

        RequestListener requestListener = new JsonRequestListener() {
            @Override
            public void onResult(Object o, Object o1) {
                Track track = (Track) o;
                url = track.getPreviewUrl();
                nameTrack.setText("Nombre de la Canción: "+ track.getTitle());
                artistTrack.setText("Artista de la Canción: "+track.getArtist().getName());
                albumName.setText("Nombre del album: "+track.getAlbum().getTitle());
                duration.setText("Duración: "+track.getDuration());
                Picasso.get().load(track.getAlbum().getBigImageUrl()).into(largeImageTrack);
            }

            @Override
            public void onUnparsedResult(String s, Object o) {

            }

            @Override
            public void onException(Exception e, Object o) {

            }
        };




        DeezerRequest requestTracks = DeezerRequestFactory.requestTrack(message);
        requestTracks.setId("Busqueda Tracks");
        deezerConnection.getDeezerConnect().requestAsync(requestTracks, requestListener);

        but_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "audio/*");
                startActivity(intent);
            }
        });


    }
}
