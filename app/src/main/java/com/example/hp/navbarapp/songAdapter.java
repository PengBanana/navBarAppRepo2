package com.example.hp.navbarapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 21/03/2018.
 */

public class songAdapter extends RecyclerView.Adapter<songAdapter.MyViewHolder> {

    PlaySongListener listener;
    private ArrayList<Song> songList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, artist;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_SongName);
            artist = (TextView) view.findViewById(R.id.tv_ArtistName);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayRequest(view.getId());
                }
            });
        }
    }


    public songAdapter(ArrayList<Song> songList) {
        this.songList = songList;
    }

    @Override
    public songAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new songAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(songAdapter.MyViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.title.setText(song.getTitle());
        holder.artist.setText(song.getArtist());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}