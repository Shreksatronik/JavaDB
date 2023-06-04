package com.example.DataBase.Entities;

import lombok.Data;

import java.io.InvalidObjectException;
import java.util.List;

@Data
public class Path {
    private String route;
    private String departureAirport;
    private String arrivalAirport;
    private String scheduledArrival;
    private String flightNo;
    private int connections;
    private int price;

    public Path(String route, String departureAirport, String arrivalAirport, String scheduledArrival, String flightNo, int connections, int price){
        this.price=price;
        this.route = route;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.scheduledArrival = scheduledArrival;
        this.flightNo = flightNo;
        this.connections = connections;
    }
}