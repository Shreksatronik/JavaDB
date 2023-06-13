package com.example.DataBase.Repositories;

import com.example.DataBase.Entities.BookedData;
import com.example.DataBase.Entities.Booking;

public interface BookingRepository {

    BookedData saveBooking(Booking booking);
}