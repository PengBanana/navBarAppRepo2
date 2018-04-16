package com.example.hp.navbarapp;

import android.os.Binder;

/**
 * Created by hp on 23/03/2018.
 */

public class MusicBinder extends Binder {
    private MusicService musicService;

    public MusicBinder() {
    }

    public MusicBinder(MusicService musicService){
        this.musicService = musicService;
    }
    public MusicService getMusicService() {
        return musicService;
    }

}
