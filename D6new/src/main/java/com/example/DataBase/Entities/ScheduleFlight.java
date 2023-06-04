package com.example.DataBase.Entities;

import lombok.Data;

@Data
public class ScheduleFlight {
    private String no;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private String otherAirport;
    private String otherCity;
}