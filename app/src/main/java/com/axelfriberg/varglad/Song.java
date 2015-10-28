package com.axelfriberg.varglad;

/**
 * Represents the songs in the songbook.
 * Created by Axel on 21/10/2015.
 */
public class Song {
    String title;
    String lyrics;

    public Song(){

    }

    public Song(String title, String lyrics){
        this.title = title;
        this.lyrics = lyrics;
    }

    public String getTitle(){
        return title;
    }

    public String getLyrics(){
        return lyrics;
    }

    @Override
    public String toString() {
        return title;
    }
}
