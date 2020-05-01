package com.example.reto2_leonardo;

public class Song {


    private long id;
    private String name;
    private String artist;
    private int yearReleased;
    private String imageURI;
    private String duration;
    private String albumName;


    public Song(long id, String name, String artist, int yearReleased, String imageURI, String duration, String albumName){
        setId(id);
        setArtist(artist);
        setName(name);
        setYearReleased(yearReleased);
        setImageURI(imageURI);
        setDuration(duration);
        setAlbumName(albumName);
    }


    public String getAlbumName() {
        return albumName;
    }

    public String getDuration() {
        return duration;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURI() {
        return imageURI;
    }

    public String getName() {
        return name;
    }

    public int getYearReleased() {
        return yearReleased;
    }
    public long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }
}
