package com.example.login2;

public class groung {
    public String league_name,location,image;

    public groung() {
    }

    public groung(String league_name, String location, String image) {
        this.league_name = league_name;
        this.location = location;
        this.image = image;
    }

    public String getleague_name() {
        return league_name;
    }

    public void setleague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
