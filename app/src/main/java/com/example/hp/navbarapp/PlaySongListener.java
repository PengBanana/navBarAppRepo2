package com.example.hp.navbarapp;

/**
 * Created by hp on 23/03/2018.
 */

public interface PlaySongListener {
    void onPlayRequest(int songIndex);
    void onSongUpdated(int songIndex);
}
