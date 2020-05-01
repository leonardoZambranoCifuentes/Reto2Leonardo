package com.example.reto2_leonardo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Permissions;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.network.connect.event.DialogListener;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AdapterPlayList adapterPlayList;
    private DeezerConnection deezerConnection;
    private Button but_search;
    private EditText txt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        adapterPlayList = new AdapterPlayList(this);

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


        recyclerView.setAdapter(adapterPlayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deezerConnection.getDeezerConnect().authorize(this, permissions, listener);


        but_search = findViewById(R.id.search_icon);

        txt_search = findViewById(R.id.txt_search);
        but_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textSearch = txt_search.getText().toString();

                RequestListener listener = new JsonRequestListener() {
                    @Override
                    public void onResult(Object o, Object o1) {

                        List<Playlist> response = (List<Playlist>) o;
                        ArrayList<PlayList> albums = new ArrayList<>(response.size());
                        for (int i=0; i < response.size(); i++){
                            Playlist p = response.get(i);
                            albums.add(new PlayList(p.getId(),p.getTitle(), p.getCreator().getName(), p.getTracks().size(), p.getSmallImageUrl(), p.getDescription(), p.getFans()));
                        }
                        adapterPlayList.setPlayLists(albums);
                        adapterPlayList.notifyDataSetChanged();
                    }

                    @Override
                    public void onUnparsedResult(String s, Object o) {

                    }

                    @Override
                    public void onException(Exception e, Object o) {

                    }
                };

                DeezerRequest requestPlaylists = DeezerRequestFactory.requestSearchPlaylists(textSearch);
                requestPlaylists.setId("Busqueda Playlists");
                deezerConnection.getDeezerConnect().requestAsync(requestPlaylists, listener);



            }
        });




    }
}
