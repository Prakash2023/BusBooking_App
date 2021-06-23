package com.minemark.platform.mybusbookingapp;

public class mybookingmodel {



    String busnumber,route,time,ACstatus,date,totalprice,totalseats,seatno,phonenumber;
    mybookingmodel()
    {

    }

    public mybookingmodel(String busnumber, String route, String time, String ACstatus, String date, String totalprice, String totalseats, String seatno,String phonenumber) {
        this.busnumber = busnumber;
        this.route = route;
        this.time = time;
        this.ACstatus = ACstatus;
        this.date = date;
        this.totalprice = totalprice;
        this.totalseats = totalseats;
        this.seatno = seatno;
        this.phonenumber=phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getACstatus() {
        return ACstatus;
    }

    public void setACstatus(String ACstatus) {
        this.ACstatus = ACstatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(String totalseats) {
        this.totalseats = totalseats;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }
}
