package com.example.a001759.tabbedapp;

/**
 * Created by 001759 on 10/30/2017.
 */

public class ListItemSongList {
String songTitle, songArtist, songLyrics;

    public ListItemSongList() {
    }

    public ListItemSongList(String songTitle, String songArtist, String songLyrics) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songLyrics = songLyrics;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongLyrics() {
        return songLyrics;
    }

    public void setSongLyrics(String songLyrics) {
        this.songLyrics = songLyrics;
    }
}
