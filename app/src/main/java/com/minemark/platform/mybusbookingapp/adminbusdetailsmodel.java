package com.minemark.platform.mybusbookingapp;

public class adminbusdetailsmodel {
    adminbusdetailsmodel()
    {

    }
    String bus_number,start_Point,stop_Point,start_Time,stop_Time,ticketfee,ACstatus,adminphonenumber;

    public adminbusdetailsmodel(String bus_number, String start_Point, String stop_Point, String start_Time, String stop_Time, String ticketfee, String ACstatus, String adminphonenumber) {
        this.bus_number = bus_number;
        this.start_Point = start_Point;
        this.stop_Point = stop_Point;
        this.start_Time = start_Time;
        this.stop_Time = stop_Time;
        this.ticketfee = ticketfee;
        this.ACstatus = ACstatus;
        this.adminphonenumber = adminphonenumber;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getStart_Point() {
        return start_Point;
    }

    public void setStart_Point(String start_Point) {
        this.start_Point = start_Point;
    }

    public String getStop_Point() {
        return stop_Point;
    }

    public void setStop_Point(String stop_Point) {
        this.stop_Point = stop_Point;
    }

    public String getStart_Time() {
        return start_Time;
    }

    public void setStart_Time(String start_Time) {
        this.start_Time = start_Time;
    }

    public String getStop_Time() {
        return stop_Time;
    }

    public void setStop_Time(String stop_Time) {
        this.stop_Time = stop_Time;
    }

    public String getTicketfee() {
        return ticketfee;
    }

    public void setTicketfee(String ticketfee) {
        this.ticketfee = ticketfee;
    }

    public String getACstatus() {
        return ACstatus;
    }

    public void setACstatus(String ACstatus) {
        this.ACstatus = ACstatus;
    }

    public String getAdminphonenumber() {
        return adminphonenumber;
    }

    public void setAdminphonenumber(String adminphonenumber) {
        this.adminphonenumber = adminphonenumber;
    }
}
