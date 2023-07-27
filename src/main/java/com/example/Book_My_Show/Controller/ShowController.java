package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Dtos.RequestDtos.AddShowDto;
import com.example.Book_My_Show.Dtos.RequestDtos.GetAllShowsDto;
import com.example.Book_My_Show.Dtos.RequestDtos.GetShowTimeDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowSeatsDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.GetAllShowsResponseDto;
import com.example.Book_My_Show.Exceptions.MovieNotFoundException;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.TheatreNotFoundException;
import com.example.Book_My_Show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping("/get-shows")
    public ResponseEntity<List<GetAllShowsResponseDto>> getAllShowsOfMovieInTheatre(@RequestBody GetAllShowsDto getAllShowsDto)
    {
        try {
            List<GetAllShowsResponseDto> responseDtoList = showService.getAllShowsOfMovieInTheatre(getAllShowsDto);
            return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
        }
        catch (RuntimeException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-movie-with-most-show")
    public ResponseEntity<String> getMovieWithMostShow(@RequestParam String atDate)
    {
        try {
            String movieName = showService.getMovieWithMostShow(atDate);
            return new ResponseEntity<>(movieName,HttpStatus.OK);
        }
        catch (RuntimeException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-show-time")
    public ResponseEntity<List<String>> getShowTime(@RequestBody GetShowTimeDto getShowTimeDto)
    {
        try{
            List<String> timeOfShows = showService.getShowTime(getShowTimeDto);
            return new ResponseEntity<>(timeOfShows,HttpStatus.OK);
        }
        catch (MovieNotFoundException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch (TheatreNotFoundException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
