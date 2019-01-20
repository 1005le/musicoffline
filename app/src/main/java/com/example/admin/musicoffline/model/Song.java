package com.example.admin.musicoffline.model;

/**
 * Created by Admin on 20/1/2019.
 */

public class Song {
    private String id;
    private String name;
    private String nameArtist;
    private String nameAlbum;
    private String songPath;

    public Song(String id, String name, String nameArtist, String nameAlbum, String songPath) {
        this.id = id;
        this.name = name;
        this.nameArtist = nameArtist;
        this.nameAlbum = nameAlbum;
        this.songPath = songPath;
    }

    public Song(String name, String nameArtist) {
        this.name = name;
        this.nameArtist = nameArtist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
