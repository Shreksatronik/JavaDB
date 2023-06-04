package com.example.DataBase.Repositories;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import com.example.DataBase.Entities.Path;
import com.example.DataBase.Util.util;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;


@Component
public class PathRepositoryImpl implements PathRepository {

    @Override
    @SneakyThrows
    public List<Path> getPaths(String departurePoint, String arrivalPoint, String departureDate,
                               int connections, String fareConditions) {
        @Cleanup Connection connection = util.getConnection();
        List<Path> res = new ArrayList<>();
        String capitalizedFareConditions = fareConditions.substring(0, 1).toUpperCase() + fareConditions.substring(1);
        var resultSet = (String.format("""
                with recursive node as (
                    select cast(f.departure_airport as varchar(50)) as route, f.departure_airport, f.arrival_airport, f.scheduled_arrival, cast(f.flight_no as varchar(50)), 0 count, cast(tad.amount as numeric) price
                    from flights as f
                             join total_amount_distinct tad on f.flight_no = tad.flight_no and fare_conditions='%s'
                    where departure_airport = '%s'
                      and f.scheduled_arrival::date = to_date('%s', 'YYYY-MM-DD')
                    union
                            
                    select cast(n.route || '->' ||  f.arrival_airport as varchar(50)) as route, n.departure_airport, f.arrival_airport, f.scheduled_arrival,  cast(n.flight_no || '->' || f.flight_no as varchar(50)), n.count + 1, n.price + tad.amount
                    from node as n
                             join flights as f on f.departure_airport = n.arrival_airport
                             join total_amount_distinct tad on f.flight_no = tad.flight_no and fare_conditions='%s'
                    where f.arrival_airport != n.route
                      and n.scheduled_arrival < f.scheduled_departure
                      and f.scheduled_arrival::date = to_date('%s', 'YYYY-MM-DD')
                      and f.departure_airport != '%s'
                      and count < %d
                )
                select * from node n
                where n.arrival_airport = '%s';
                        """, capitalizedFareConditions, departurePoint, departureDate, capitalizedFareConditions, departureDate, departurePoint, connections, arrivalPoint));
        @Cleanup Statement statement = connection.createStatement();
        var resSet = statement.executeQuery(resultSet);
        while (resSet.next()) {
                res.add(new Path(
                   resSet.getString("route"),
                   resSet.getString("departure_airport"),
                        resSet.getString("arrival_airport"),
                        resSet.getString("scheduled_arrival"),
                        resSet.getString("flight_no"),
                        resSet.getInt("count"),
                        resSet.getInt("price")
                ));
        }
        return res;
    }
}