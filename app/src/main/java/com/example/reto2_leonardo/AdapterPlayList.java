package com.example.reto2_leonardo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterPlayList extends RecyclerView.Adapter<AdapterPlayList.ViewHolder> {

    private ArrayList<PlayList> playLists;
    private MainActivity activity;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView numberSongs;
        public TextView creator;
        public TextView nameList;
        public ImageView imageUrl;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numberSongs = itemView.findViewById(R.id.songs);
            creator = itemView.findViewById(R.id.creator);
            nameList = itemView.findViewById(R.id.nameList);
            imageUrl = itemView.findViewById(R.id.imageURLPlaylist);
            linearLayout = itemView.findViewById(R.id.layoutListener);
        }
    }

    public AdapterPlayList(MainActivity activity){
        playLists = new ArrayList<>();
        this.activity = activity;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(ArrayList<PlayList> playLists) {
        this.playLists = playLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View playListView = inflater.inflate(R.layout.item_playlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(playListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        PlayList playList = playLists.get(position);
        holder.numberSongs.setText("Numero de Canciones: "+playList.getNumberSongs());
        holder.creator.setText("Usuario de la lista: "+playList.getOwner());
        holder.nameList.setText("Nombre de la lista: "+playList.getName());
        Picasso.get().load(playList.getImageURI()).into(holder.imageUrl);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, SongActivity.class);
                i.putExtra("ID Playlist",playLists.get(position).getID());
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }
}