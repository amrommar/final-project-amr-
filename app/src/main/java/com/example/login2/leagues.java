package com.example.login2;

public class leagues {
    public String email, league_name, location,image;

    public leagues() {

    }

    public leagues(String email,String image,String league_name, String location) {
        this.email = email;
        this.league_name = league_name;
        this.location = location;
        this.image = image;


    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeaguename(String league_name) {
        this.league_name = league_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
