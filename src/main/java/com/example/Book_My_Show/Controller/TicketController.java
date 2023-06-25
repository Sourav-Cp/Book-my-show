package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Dtos.RequestDtos.TicketRequestDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.Book_My_Show.Exceptions.SeatUnavailableException;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.UserNotFoundException;
import com.example.Book_My_Show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto)
    {
        try {
            TicketResponseDto responseDto = ticketService.bookTicket(ticketRequestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch (ShowNotFoundException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch (SeatUnavailableException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
