package com.example.Controllers;


import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Constants.Mappings;
import com.example.DataBase.Repositories.PathRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class PathRestController {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired private PathRepository pathRepository;

    @GetMapping(Mappings.PATHS)
    @SneakyThrows
    public String getPaths(@RequestParam String departurePoint,
                           @RequestParam String arrivalPoint,
                           @RequestParam String departureDate,
                           @RequestParam int connections,
                           @RequestParam String fareConditions) {

        return new Gson().toJson(pathRepository.getPaths(
                departurePoint, arrivalPoint, departureDate, connections, fareConditions));
    }
}