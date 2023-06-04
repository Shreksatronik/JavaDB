package com.example.DataBase.Repositories;

import com.example.DataBase.Entities.BookedData;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.Cleanup;
import lombok.SneakyThrows;
import com.example.DataBase.Entities.Booking;
import com.example.DataBase.Util.util;
import org.springframework.stereotype.Component;

@Component
public class BookingRepositoryImpl implements BookingRepository {
    private static Logger log = Logger.getLogger(BookingRepositoryImpl.class.getName());

    @Override
    @SneakyThrows
    public BookedData saveBooking(Booking booking) {
        log.info(booking.toString());
        @Cleanup Connection connection = util.getConnection();
        String sqlQuery = String.format("""
                select flight_id, aircraft_code from bookings.flights
                where flight_no='%s' and status='Scheduled' and
                scheduled_departure::date = to_date('%s', 'YYYY-MM-DD'); 
                """, booking.getFlightNo(), booking.getDepartureDate());

        try {
            connection.setAutoCommit(false);
            @Cleanup Statement statement = connection.createStatement();
            String aircraftCode;
            String flightId;
            var queryRes = statement.executeQuery(sqlQuery);
            if (!queryRes.next()) {
                log.log(Level.SEVERE, "No flights found for " + booking.getFlightNo());
                throw new RuntimeException();
            } else {
                aircraftCode = queryRes.getString("aircraft_code");
                flightId = queryRes.getString("flight_id");
            }
            int seats = 0;
            //free seats
            sqlQuery = String.format("""
                        select count(*)-(select count(*) from bookings.ticket_flights where fare_conditions = '%s' and flight_id = '%s') left_places 
                        from bookings.seats 
                        where aircraft_code = '%s';
                    """, booking.getFareConditions(), flightId, aircraftCode);
            connection.setAutoCommit(false);
            @Cleanup Statement seatsStatement = connection.createStatement();
            var resSet = seatsStatement.executeQuery(sqlQuery);
            while (resSet.next()) {
                seats = resSet.getInt("left_places");
            }
            if (seats <= 0) {
                log.log(Level.SEVERE, "No seats " + booking.getFareConditions() + " for flight " + booking.getFlightNo() + " found");
                throw new RuntimeException();
            }
            int price = 0;
            sqlQuery = String.format("""
                        select amount
                        from total_amount_distinct
                        where flight_no='%s' and fare_conditions='%s';
                    """, booking.getFlightNo(), booking.getFareConditions());
            statement = connection.createStatement();
            resSet = statement.executeQuery(sqlQuery);
            while (resSet.next()) {
                price = resSet.getInt("amount");
            }
            if (price == 0){
                throw new RuntimeException("Нельзя забронировать");
            }
            String bookRef = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase(Locale.getDefault());
            String ticketNo = UUID.randomUUID().toString().replace("-", "").substring(0, 11).toUpperCase(Locale.getDefault());
            boolean flag = true;
            while (flag) {
                sqlQuery = String.format("""
                            select book_ref
                                    from bookings.bookings
                                    where book_ref='%s';
                        """, bookRef);
                statement = connection.createStatement();
                resSet = statement.executeQuery(sqlQuery);
                if (resSet.next()) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    bookRef = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase(Locale.getDefault());
                } else {
                    flag = false;
                }
            }
            boolean flag2 = true;
            while (flag2) {
                sqlQuery = String.format("""
                            select ticket_no from bookings.tickets where ticket_no='%s';
                        """, ticketNo);
                statement = connection.createStatement();
                resSet = statement.executeQuery(sqlQuery);
                if (resSet.next()) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    ticketNo = UUID.randomUUID().toString().replace("-", "").substring(0, 11).toUpperCase(Locale.getDefault());
                } else {
                    flag2 = false;
                }
            }
            log.info("Ticket " + ticketNo);
            log.info("Booking " + bookRef);
            sqlQuery = String.format("""
                    insert into bookings.bookings (book_ref, book_date, total_amount)\s
                    values ('%s', now(), %d)
                    returning *;
                    """, bookRef, price);
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
            connection.commit();
            sqlQuery = String.format("""
                    insert into bookings.tickets (ticket_no, book_ref, passenger_id, passenger_name, contact_data)
                    values ('%s', '%s', '%s', '%s', '{"email": "%s", "phone": "%s"}')
                    returning *;
                            """, ticketNo, bookRef, booking.getPassengerId(), booking.getPassengerName(), booking.getContact().getEmail(), booking.getContact().getPhone());
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
            connection.commit();
            sqlQuery = String.format("""
                    insert into bookings.ticket_flights (ticket_no, flight_id, fare_conditions, amount)\s
                    values ('%s', '%s', '%s', %d)
                    returning *;
                            """, ticketNo, flightId, booking.getFareConditions(), price);
            statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
            connection.commit();
            return new BookedData(ticketNo, price);
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        return null;
    }
}