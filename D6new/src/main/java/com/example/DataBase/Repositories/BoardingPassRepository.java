package com.example.DataBase.Repositories;

import com.example.DataBase.Entities.BoardingPass;
import java.util.List;

public interface BoardingPassRepository {
    List<BoardingPass> generateBoardingPasses(String ticketNo);
}