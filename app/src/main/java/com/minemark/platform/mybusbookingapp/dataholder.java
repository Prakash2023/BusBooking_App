package com.minemark.platform.mybusbookingapp;

public class dataholder {
    String Bus_name;
    String Start_Point;
    String Stop_Point;
    String Start_Time;
    String Stop_Time;
    String Ticketfee;
    String ACstatus;
    String Bus_number;
    String adminphonenumber;

    public dataholder(String ACstatus,String bus_name, String bus_number,String start_Point,String start_Time, String stop_Point, String stop_Time, String ticketfee,String adminphonenumber ) {

        this.ACstatus = ACstatus;
        this.Bus_name = bus_name;
        this.Bus_number = bus_number;
        this.Start_Point = start_Point;
        this.Start_Time = start_Time;
        this.Stop_Point = stop_Point;
        this.Stop_Time = stop_Time;
        this.Ticketfee = ticketfee;
        this.adminphonenumber=adminphonenumber;


    }

    public String getAdminphonenumber() {
        return adminphonenumber;
    }

    public void setAdminphonenumber(String adminphonenumber) {
        this.adminphonenumber = adminphonenumber;
    }

    public String getBus_name() {
        return Bus_name;
    }

    public void setBus_name(String bus_name) {
        Bus_name = bus_name;
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

    public String getBus_number() {
        return Bus_number;
    }

    public void setBus_number(String bus_number) {
        Bus_number = bus_number;
    }
}


