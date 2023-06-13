package com.example.DataBase.Repositories;

import java.sql.Connection;
import java.sql.Statement;

import lombok.Cleanup;
import lombok.SneakyThrows;
import com.example.DataBase.Entities.BoardingPass;
import com.example.DataBase.Util.util;
import org.springframework.stereotype.Component;

@Component
public class BoardingPassRepositoryImpl implements BoardingPassRepository {

    @Override
    @SneakyThrows
    public BoardingPass generateBoardingPasses(String ticketNo) {
        @Cleanup Connection connection = util.getConnection();
        @Cleanup Statement flightIdsStatement = connection.createStatement();
        //if exist
        @Cleanup var flightIdsResultSet = flightIdsStatement.executeQuery(String.format("""
        select flight_id from ticket_flights
        where ticket_no = '%s';
        """, ticketNo));
        if (!flightIdsResultSet.next()){
            throw new RuntimeException("This user didn't bought a ticket for flight");
        }
        String sqlRequest = String.format("select flight_id, fare_conditions from bookings.ticket_flights where ticket_no = '%s';", ticketNo);
        int flightId = 0;
        String fareCondition = "";
        Statement statement = connection.createStatement();
        var resSet = statement.executeQuery(sqlRequest);
        while (resSet.next()){
            flightId = resSet.getInt("flight_id");
            fareCondition = resSet.getString("fare_conditions");
        }
        sqlRequest = String.format("select aircraft_code from bookings.flights where flight_id = %d;", flightId);
        String code = "";
        statement = connection.createStatement();
        resSet = statement.executeQuery(sqlRequest);
        while (resSet.next()){
            code = resSet.getString("aircraft_code");
        }
        //n seat
        sqlRequest = String.format(
                "select seat_no from bookings.seats " +
                        "where aircraft_code = '%s'" +
                        " and fare_conditions = '%s'" +
                        " and seat_no not in (select seat_no from bookings.boarding_passes where flight_id = %d) " +
                        " limit 1;", code, fareCondition, flightId);
        String seat = "";
        statement = connection.createStatement();
        resSet = statement.executeQuery(sqlRequest);

        if (resSet.next()){
            seat = resSet.getString("seat_no");
        }
        else {
            throw new RuntimeException("Sorry, no free seats found");
        }
        sqlRequest = String.format("select count(*) from bookings.boarding_passes where flight_id = %s and seat_no!='%s';", flightId, seat);
        int boarding = 0;
        //n board pass
        statement = connection.createStatement();
        resSet = statement.executeQuery(sqlRequest);
        while (resSet.next()){
            boarding = resSet.getInt("count");
        }
        boarding += 1;
        connection.setAutoCommit(false);
        sqlRequest = String.format("insert into bookings.boarding_passes (ticket_no, flight_id, boarding_no, seat_no) VALUES ('%s', %d, %d, '%s') returning *;", ticketNo, flightId, boarding, seat);
        statement = connection.createStatement();
        try {
            statement.executeQuery(sqlRequest);
            connection.commit();
        } catch (Exception e){
            throw new RuntimeException("This ticket is already checked in");
        }
        connection.setAutoCommit(true);
        return new BoardingPass(seat, boarding);
    }
}