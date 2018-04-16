package com.example.hp.navbarapp;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{
    private MusicBinder musicBind;
    PlaySongListener songListener;
    private MediaPlayer mediaPlayer;
    private ArrayList<Song> songList;
    private int songIndex;
    public MusicService() {
    }
    public void onCreate(){
        super.onCreate();
        this.songIndex = 0;
        this.mediaPlayer = new MediaPlayer();

        this.mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //sir bakit nag breakthrough line^
        this.mediaPlayer.setOnPreparedListener(this);
        this.mediaPlayer.setOnCompletionListener(this);
        this.mediaPlayer.setOnErrorListener(this);
    }

    public void setSongList(ArrayList<Song> songList, PlaySongListener songListener){
        this.songList = songList;
        this.songListener=songListener;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return this.musicBind;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onUnbind(Intent intent) {
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
        return false;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
    public void setSong(int songIndex) {
        this.songIndex=songIndex;
    }
    public void playSong(){
        this.mediaPlayer.reset();
        Song playSong = this.songList.get(this.songIndex);
        long currSong = playSong.getId();
        songListener.onSongUpdated(songIndex);
        Uri trackUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, currSong
        );
        try{
            this.mediaPlayer.setDataSource(getApplicationContext(), trackUri);
        } catch (IOException e) {
            Log.d("Music Service", "error");
        }
    }
}
