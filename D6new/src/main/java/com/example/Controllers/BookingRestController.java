package com.example.Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Constants.Mappings;
import com.example.DataBase.Entities.Booking;
import com.example.DataBase.Repositories.BookingRepository;

import java.io.InvalidObjectException;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class BookingRestController {

    @Autowired private BookingRepository bookingRepository;

    @PostMapping(Mappings.BOOKING)
    public String makeBooking(@RequestBody String body) {
        Booking booking;
        try {
            booking = new Gson().fromJson(body, Booking.class);
        } catch (JsonSyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new Gson().toJson(bookingRepository.saveBooking(booking));
    }
}