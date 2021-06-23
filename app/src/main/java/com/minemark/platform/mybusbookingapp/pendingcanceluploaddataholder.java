package com.minemark.platform.mybusbookingapp;

public class pendingcanceluploaddataholder {
    String username,phonenumber,busnumber,route,date,totalseats,totalprice,adminphonenumber,acstatus,time,seatno,status,canceltime;

    public pendingcanceluploaddataholder(String username, String phonenumber, String busnumber, String route, String date, String totalseats, String totalprice, String adminphonenumber, String acstatus, String time, String seatno, String status, String canceltime) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.busnumber = busnumber;
        this.route = route;
        this.date = date;
        this.totalseats = totalseats;
        this.totalprice = totalprice;
        this.adminphonenumber = adminphonenumber;
        this.acstatus = acstatus;
        this.time = time;
        this.seatno = seatno;
        this.status = status;
        this.canceltime = canceltime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(String totalseats) {
        this.totalseats = totalseats;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getAdminphonenumber() {
        return adminphonenumber;
    }

    public void setAdminphonenumber(String adminphonenumber) {
        this.adminphonenumber = adminphonenumber;
    }

    public String getAcstatus() {
        return acstatus;
    }

    public void setAcstatus(String acstatus) {
        this.acstatus = acstatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(String canceltime) {
        this.canceltime = canceltime;
    }
}
