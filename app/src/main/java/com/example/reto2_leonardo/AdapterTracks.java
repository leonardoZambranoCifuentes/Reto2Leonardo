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

public class AdapterTracks extends RecyclerView.Adapter<AdapterTracks.ViewHolder>{

    private ArrayList<Song> songs;
    private SongActivity activitySong;


    public AdapterTracks(SongActivity activitySong){
        songs = new ArrayList<>();
        this.activitySong = activitySong;
    }


    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View trackView = inflater.inflate(R.layout.item_track, parent, false);
        ViewHolder viewHolder = new ViewHolder(trackView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
         Song song = songs.get(position);

         TextView yearTrack = holder.yearTrack;
         TextView artist = holder.artist;
         TextView nametrack = holder.nametrack;
         ImageView imageUrltrack = holder.imageUrltrack;
         LinearLayout linearLayoutTrack = holder.linearLayoutTrack;

         yearTrack.setText("Año de lanzamiento: "+ song.getYearReleased());
         artist.setText("Nombre del Artista: "+song.getArtist());
         nametrack.setText("Nombre de la Canción: "+song.getName());
         Picasso.get().load(song.getImageURI()).into(holder.imageUrltrack);

         linearLayoutTrack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(activitySong, trackPlayerActivity.class);
                 i.putExtra("ID Track", songs.get(position).getId());
                 activitySong.startActivity(i);
             }
         });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView yearTrack;
        public TextView artist;
        public TextView nametrack;
        public ImageView imageUrltrack;
        public LinearLayout linearLayoutTrack;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yearTrack = itemView.findViewById(R.id.yearTrack);
            artist = itemView.findViewById(R.id.artistTrack);
            nametrack = itemView.findViewById(R.id.nameTrack);
            imageUrltrack = itemView.findViewById(R.id.imageURLtrack);
            linearLayoutTrack = itemView.findViewById(R.id.layoutListenerTrack);
        }


    }



}
