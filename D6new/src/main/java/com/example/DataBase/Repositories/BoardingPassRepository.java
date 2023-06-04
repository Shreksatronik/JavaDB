package com.example.DataBase.Repositories;

import com.example.DataBase.Entities.BoardingPass;
import java.util.List;

public interface BoardingPassRepository {
    BoardingPass generateBoardingPasses(String ticketNo);
}