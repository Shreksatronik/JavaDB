package com.example.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Constants.Mappings;
import com.example.DataBase.Repositories.ScheduleFlightRepository;
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ScheduleFlightRestController {

    @Autowired private ScheduleFlightRepository scheduleFlightRepository;

    @GetMapping(Mappings.SCHEDULE_FLIGHTS)
    public String getScheduleFlights(@RequestParam boolean arriving,
                                     @RequestParam String airport,
                                     @RequestParam int day) {

        return new Gson().toJson(arriving ?
                scheduleFlightRepository.getArrivingFlights(airport, day) :
                scheduleFlightRepository.getDepartingFlights(airport, day));
    }
}