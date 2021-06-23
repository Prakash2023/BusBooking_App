package com.minemark.platform.mybusbookingapp;

public class adminsignupdataholder {
    String username,phonenumber,email,check;

    public adminsignupdataholder(String username, String phonenumber, String email, String check) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.check = check;
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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
