package com.example.login2.ui.home;

public class acadm {
    public String acadamy_name ,location,image;

    public acadm() {
    }

    public acadm(String acadamy_name, String location, String image) {
        this.acadamy_name = acadamy_name;
        this.location = location;
        this.image = image;
    }

    public String getAcadamy_name() {
        return acadamy_name;
    }

    public void setAcadamy_name(String acadamy_name) {
        this.acadamy_name = acadamy_name;
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
