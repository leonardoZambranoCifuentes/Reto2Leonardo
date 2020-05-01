package com.example.reto2_leonardo;

public class PlayList {
    private long ID;
    private String name;
    private String owner;
    private int numberSongs;
    private int numberFans;
    private String imageURI;
    private String description;

    public PlayList(long ID, String name, String owner, int numberSongs, String imageURI, String description, int numberFans){
        setID(ID);
        setName(name);
        setOwner(owner);
        setNumberSongs(numberSongs);
        setImageURI(imageURI);
        setDescription(description);
        setNumberFans(numberFans);
    }

    public int getNumberFans() {
        return numberFans;
    }

    public void setNumberFans(int numberFans) {
        this.numberFans = numberFans;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getNumberSongs() {
        return numberSongs;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setNumberSongs(int numberSongs) {
        this.numberSongs = numberSongs;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

}
