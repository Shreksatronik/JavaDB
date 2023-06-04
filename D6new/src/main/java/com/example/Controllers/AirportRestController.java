package com.example.Controllers;
import com.example.DataBase.Repositories.AirportRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Constants.Mappings;

import java.util.Objects;


@RestController
@RequestMapping(value = "/", produces = "application/json")
public class AirportRestController {

    @Autowired private AirportRepository airportRepository;

    @GetMapping(Mappings.AIRPORTS)
    public String getAirportsByCity(@RequestParam String city) {
        System.out.println(city);
        return new Gson().toJson(Objects.equals(city, "") ?
                airportRepository.getAirports() :
                airportRepository.getAirports(city));
    }

//    @GetMapping(Mappings.AIRPORTS)
//    public String getAirports(){
//        return new Gson().
//    }
}