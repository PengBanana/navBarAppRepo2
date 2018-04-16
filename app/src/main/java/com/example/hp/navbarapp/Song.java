package com.example.hp.navbarapp;

/**
 * Created by hp on 21/03/2018.
 */

public class Song {
    private String title;
    private String Artist;
    private long id;

    public Song(String title, String artist, long id) {
        this.title = title;
        Artist = artist;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Song(String title, String artist) {

        this.title = title;
        Artist = artist;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }
}
