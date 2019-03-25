package com.marvedie.servicesmarketapp;

public class GoodConductUploadClass {

    public String phone;
    public String password;
    public String town;
    public String imageURL;

    public GoodConductUploadClass() {

    }

    public GoodConductUploadClass(String phone, String password, String town, String imageURL) {
        this.phone = phone;
        this.password = password;
        this.town = town;
        this.imageURL = imageURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}


