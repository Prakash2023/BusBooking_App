package com.minemark.platform.mybusbookingapp;

public class userbankdataholder {
    String username,phonenumber,email,userbankaccountname,userbankname,userbankaccount1,userbankifsc;

    public userbankdataholder(String username, String phonenumber, String email, String userbankaccountname, String userbankname, String userbankaccount1, String userbankifsc) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.userbankaccountname = userbankaccountname;
        this.userbankname = userbankname;
        this.userbankaccount1 = userbankaccount1;
        this.userbankifsc = userbankifsc;
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

    public String getUserbankaccountname() {
        return userbankaccountname;
    }

    public void setUserbankaccountname(String userbankaccountname) {
        this.userbankaccountname = userbankaccountname;
    }

    public String getUserbankname() {
        return userbankname;
    }

    public void setUserbankname(String userbankname) {
        this.userbankname = userbankname;
    }

    public String getUserbankaccount1() {
        return userbankaccount1;
    }

    public void setUserbankaccount1(String userbankaccount1) {
        this.userbankaccount1 = userbankaccount1;
    }

    public String getUserbankifsc() {
        return userbankifsc;
    }

    public void setUserbankifsc(String userbankifsc) {
        this.userbankifsc = userbankifsc;
    }
}
