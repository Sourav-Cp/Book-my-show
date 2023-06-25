package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Dtos.RequestDtos.AddTheatreDto;
import com.example.Book_My_Show.Dtos.RequestDtos.AddTheatreSeatsDto;
import com.example.Book_My_Show.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public String addTheatre(@RequestBody AddTheatreDto addTheatreDto)
    {
        return theatreService.addTheatre(addTheatreDto);
    }

    @PostMapping("/add-theatre-seats")
    public String addTheatreSeats(@RequestBody AddTheatreSeatsDto addTheatreSeatsDto)
    {
       return theatreService.addTheatreSeats(addTheatreSeatsDto);
    }
}
