package com.example.Controllers;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Constants.Mappings;
import com.example.DataBase.Repositories.BoardingPassRepository;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class BoardingPassesRestController {

    @Autowired private BoardingPassRepository boardingPassRepository;

    @GetMapping(Mappings.BOARDING_PASSES)
    public String getBoardingPasses(@RequestParam String ticketNo) {
        System.out.println(ticketNo);
        System.out.println(boardingPassRepository.generateBoardingPasses(ticketNo).size());
        return new Gson().toJson(boardingPassRepository.generateBoardingPasses(ticketNo));
    }
}