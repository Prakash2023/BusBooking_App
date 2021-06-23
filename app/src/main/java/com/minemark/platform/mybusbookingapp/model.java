package com.minemark.platform.mybusbookingapp;

public class model {
    String Bus_number, Start_Point,Stop_Point,Start_Time,Stop_Time,Ticketfee,ACstatus,adminphonenumber;
    model()
    {

    }

    public model(String bus_number, String start_Point, String stop_Point, String start_Time, String stop_Time, String ticketfee, String ACstatus, String adminphonenumber) {
        this.Bus_number = bus_number;
        this.Start_Point = start_Point;
        this.Stop_Point = stop_Point;
        this.Start_Time = start_Time;
        this.Stop_Time = stop_Time;
        this.Ticketfee = ticketfee;
        this.ACstatus = ACstatus;
        this.adminphonenumber = adminphonenumber;
    }

    public String getBus_number() {
        return Bus_number;
    }

    public void setBus_number(String bus_number) {
        Bus_number = bus_number;
    }

    public String getStart_Point() {
        return Start_Point;
    }

    public void setStart_Point(String start_Point) {
        Start_Point = start_Point;
    }

    public String getStop_Point() {
        return Stop_Point;
    }

    public void setStop_Point(String stop_Point) {
        Stop_Point = stop_Point;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String start_Time) {
        Start_Time = start_Time;
    }

    public String getStop_Time() {
        return Stop_Time;
    }

    public void setStop_Time(String stop_Time) {
        Stop_Time = stop_Time;
    }

    public String getTicketfee() {
        return Ticketfee;
    }

    public void setTicketfee(String ticketfee) {
        Ticketfee = ticketfee;
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
