package com.example.hp.navbarapp;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class musicActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public ArrayList<Song> songList = new ArrayList<>();
    private RecyclerView recyclerView;
    private songAdapter rAdapter;
    private MusicService musicService;
    private Intent playIntent;
    private boolean musicBound = false;
    private ServiceConnection musicConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //on
                Log.d("Permissions","first condition");
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
                Log.d("Permissions", " 2nd");
            }
        } else {
            Log.d("Permissions","3rd condition");
            loadSongsFromStorage();
            setupUI();
            //permission granted
        }


        setupMusicService();
        startMusicService();

    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicService=null;
        super.onDestroy();
    }

    private void startMusicService() {
        if(this.playIntent==null){
            this.playIntent = new Intent(this, MusicService.class);
            bindService(this.playIntent, this.musicConnection, Context.BIND_AUTO_CREATE);
            startService(this.playIntent);
        }
    }

    public void setupMusicService() {
        this.musicConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                MusicBinder binder = (MusicBinder) service;
                musicService = binder.getMusicService();
                musicService.setSongList(songList, new PlaySongListener() {
                    @Override
                    public void onPlayRequest(int songIndex) {
                        musicService.setSong(songIndex);
                        musicService.playSong();
                    }

                    @Override
                    public void onSongUpdated(int songIndex) {
                        musicService.setSong(songIndex);
                        musicService.playSong();
                    }
                });
                musicBound=true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                musicBound=false;
            }

        };

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE: {
                //if request is cancelled, the result arrays are empty
                if(grantResults.length>0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("Permission Granted", "onRequestPermissionsResult: ");
                    //permission was granted yay! Do the contacts related task you need to do
                    this.loadSongsFromStorage();

                    this.setupUI();
                }
                else{
                    Log.d("Permission Granted", "onRequestPermissionsResult: ");
                    //permission denied boo! disable the functionality that depends on this permission
                    //finish();
                }
                return;
            }
        }
    }
    private void loadSongsFromStorage(){
        Log.d("Loading:", "loadSongsFromStorage()");
        ContentResolver musicResolver = this.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null,null,null,null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            do{
                Log.d("Music", "Loading");
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new Song(thisTitle, thisArtist, thisId));
                Log.d("Added", thisTitle);
            }
            while (musicCursor.moveToNext());
        }
    }
    private void setupUI(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_songlist);

        rAdapter = new songAdapter(songList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
        });
        recyclerView.setAdapter(rAdapter);
        Log.d("Songs:", ""+songList);
    }

}
