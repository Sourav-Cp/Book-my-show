package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Dtos.RequestDtos.AddShowDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowSeatsDto;
import com.example.Book_My_Show.Exceptions.MovieNotFoundException;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.TheatreNotFoundException;
import com.example.Book_My_Show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody AddShowDto addShowDto)
    {
        try {
            String msg = showService.addShow(addShowDto);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        catch (MovieNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (TheatreNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-show-seats")
    public ResponseEntity<String> associateShowSeats(@RequestBody ShowSeatsDto showSeatsDto)
    {
        try {
            String msg = showService.associateShowSeats(showSeatsDto);
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }
        catch (ShowNotFoundException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
