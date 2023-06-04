package com.example.DataBase.Entities;

import lombok.Data;

import java.io.InvalidObjectException;

@Data
public class Booking {

    private String flightNo;
    private String departureDate;
    private String passengerId;
    private String passengerName;
    private Contact contact;
    private String fareConditions;

}