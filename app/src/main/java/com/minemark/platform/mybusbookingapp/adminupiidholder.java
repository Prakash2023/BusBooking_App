package com.minemark.platform.mybusbookingapp;

public class adminupiidholder {
    String email,phonenumber,username,check,adminupiname,adminupicompanyname,adminupiid;

    public adminupiidholder(String email, String phonenumber, String username, String check, String adminupiname, String adminupicompanyname, String adminupiid) {
        this.email = email;
        this.phonenumber = phonenumber;
        this.username = username;
        this.check = check;
        this.adminupiname = adminupiname;
        this.adminupicompanyname = adminupicompanyname;
        this.adminupiid = adminupiid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getAdminupiname() {
        return adminupiname;
    }

    public void setAdminupiname(String adminupiname) {
        this.adminupiname = adminupiname;
    }

    public String getAdminupicompanyname() {
        return adminupicompanyname;
    }

    public void setAdminupicompanyname(String adminupicompanyname) {
        this.adminupicompanyname = adminupicompanyname;
    }

    public String getAdminupiid() {
        return adminupiid;
    }

    public void setAdminupiid(String adminupiid) {
        this.adminupiid = adminupiid;
    }
}
