package com.minemark.platform.mybusbookingapp;

public class usersignupdataholder {
    String username,phonenumber,email;

    public usersignupdataholder(String username, String phonenumber, String email) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
