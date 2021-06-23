package com.minemark.platform.mybusbookingapp;

public class myseatproceed {
    String phonenumber;
    String busnumber;
    String date;
    String totalseats;
    String seatno;
    String totalprice;
    String route;
    String time;
    String acstatus;
    String username;
    String bookingdate;
    String adminphonenumber;

    public myseatproceed(String phonenumber, String busnumber, String date,String route, String time,
                         String totalseats, String seatno, String totalprice,  String acstatus,String username, String bookingdate, String adminphonenumber) {
        this.phonenumber = phonenumber;
        this.busnumber = busnumber;
        this.date = date;
        this.totalseats = totalseats;
        this.seatno = seatno;
        this.totalprice = totalprice;
        this.route = route;
        this.time = time;
        this.acstatus = acstatus;
        this.username=username;
        this.bookingdate=bookingdate;
        this.adminphonenumber=adminphonenumber;
    }

    public String getAdminphonenumber() {
        return adminphonenumber;
    }

    public void setAdminphonenumber(String adminphonenumber) {
        this.adminphonenumber = adminphonenumber;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
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

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
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

    public String getAcstatus() {
        return acstatus;
    }

    public void setAcstatus(String acstatus) {
        this.acstatus = acstatus;
    }
}
