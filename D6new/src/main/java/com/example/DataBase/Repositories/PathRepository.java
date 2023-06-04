package com.example.DataBase.Repositories;

import java.util.Date;
import java.util.List;
import com.example.DataBase.Entities.Path;

public interface PathRepository {
    List<Path> getPaths(String departurePoint, String arrivalPoint, String departureDate, int connections, String fareConditions);
}