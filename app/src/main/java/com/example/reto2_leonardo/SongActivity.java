package com.example.reto2_leonardo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.deezer.sdk.model.Permissions;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.event.DialogListener;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SongActivity extends AppCompatActivity {


    private RecyclerView tracks;
    private TextView namePlaylist;
    private ImageView imagePlaylist;
    private TextView descriptionPlaylist;
    private TextView fansSongsPlaylist;
    private DeezerConnection deezerConnection;
    private AdapterTracks adapterTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        adapterTracks = new AdapterTracks(this);
        tracks = findViewById(R.id.tracksPlaylist);
        namePlaylist = findViewById(R.id.namePlaylist);
        imagePlaylist = findViewById(R.id.imagePlaylist);
        descriptionPlaylist = findViewById(R.id.descriptionPlaylist);
        fansSongsPlaylist = findViewById(R.id.fansSongs);

        deezerConnection = new DeezerConnection();

        String[] permissions = new String[] {
                Permissions.BASIC_ACCESS,
                Permissions.MANAGE_LIBRARY,
                Permissions.LISTENING_HISTORY };

        DialogListener listener = new DialogListener() {

            public void onComplete(Bundle values) {}

            public void onCancel() {}

            public void onException(Exception e) {}
        };

        tracks.setAdapter(adapterTracks);
        tracks.setLayoutManager(new LinearLayoutManager(this));
        deezerConnection.getDeezerConnect().authorize(this, permissions, listener);

        RequestListener requestListener = new JsonRequestListener() {
            @Override
            public void onResult(Object o, Object o1) {
                Playlist playlist = (Playlist) o;
                namePlaylist.setText("Nombre del Playlist: "+playlist.getTitle());
                descriptionPlaylist.setText("Descripción: "+playlist.getDescription());
                fansSongsPlaylist.setText("Fans: "+playlist.getFans()+" Canciones: "+playlist.getTracks().size());
                Picasso.get().load(playlist.getBigImageUrl()).into(imagePlaylist);
                List<Track> response = (List<Track>) playlist.getTracks();
                ArrayList<Song> songs = new ArrayList<>(response.size());
                for (int i=0; i < response.size(); i++){
                    Track t = response.get(i);
                    Date date = t.getReleaseDate();
                    int year = 0;
                    if(date!=null){
                        year = date.getYear();
                    }
                    songs.add(new Song(t.getId(),
                            t.getTitle(),
                            t.getArtist().getName(), year,t.getAlbum().getSmallImageUrl(),t.getDuration()+"",t.getAlbum().getTitle()));
                }
                adapterTracks.setSongs(songs);
                adapterTracks.notifyDataSetChanged();
            }

            @Override
            public void onUnparsedResult(String s, Object o) {

            }

            @Override
            public void onException(Exception e, Object o) {

            }
        };

        Intent intent = getIntent();
        long message = (long) intent.getExtras().get("ID Playlist");

        DeezerRequest requestTracks = DeezerRequestFactory.requestPlaylist(message);
        requestTracks.setId("Busqueda Tracks");
        deezerConnection.getDeezerConnect().requestAsync(requestTracks, requestListener);




    }
}
