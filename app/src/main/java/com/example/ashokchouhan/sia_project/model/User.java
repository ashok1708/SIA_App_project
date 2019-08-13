package com.example.ashokchouhan.sia_project.model;

public class User {

    private String Ticket_Number,Name,Destination;

    public  User (){

    }

    public void setTicket_Number(String ticket_Number) {
        Ticket_Number = ticket_Number;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getTicket_Number() {

        return Ticket_Number;
    }

    public String getName() {
        return Name;
    }

    public String getDestination() {
        return Destination;
    }
}
