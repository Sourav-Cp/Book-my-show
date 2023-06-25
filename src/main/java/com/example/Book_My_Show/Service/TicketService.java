package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Dtos.RequestDtos.TicketRequestDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.TicketResponseDto;
import com.example.Book_My_Show.Exceptions.SeatUnavailableException;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.UserNotFoundException;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.ShowSeats;
import com.example.Book_My_Show.Models.Ticket;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender emailSender;

    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws UserNotFoundException, ShowNotFoundException,SeatUnavailableException
    {
        int userId = ticketRequestDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User not Exist");

        int showId = ticketRequestDto.getShowId();
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) throw new ShowNotFoundException("Show is not Available");

        List<String> requestedSeats = ticketRequestDto.getRequestedSeats();

        Show show = showOptional.get();
        User user = userOptional.get();

        List<ShowSeats> showSeatsList = show.getShowSeatsList();
        boolean isValid = true;

        for(ShowSeats showSeats : showSeatsList)
        {
            String seatNo = showSeats.getSeatNo();
            if(requestedSeats.contains(seatNo) && showSeats.isAvailable() == false)
            {
                isValid = false;
            }
        }

        if(! isValid) throw new SeatUnavailableException("Seat is Not Available");

        int totalPrice = 0;

        for(ShowSeats showSeats : showSeatsList)
        {
            String seatNo = showSeats.getSeatNo();
            if(requestedSeats.contains(seatNo))
            {
                totalPrice += showSeats.getPrice();
                showSeats.setAvailable(false);
            }
        }
        Ticket ticket = new Ticket();
        ticket.setTotalPrice(totalPrice);

        StringBuilder sb = new StringBuilder();

        for (String no : requestedSeats)
        {
            sb.append(no);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);

        String seats = sb.toString();

        ticket.setBookedSeats(seats);
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);

        showRepository.save(show);
        userRepository.save(user);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String body = "Hi "+user.getName()+" ! /n"+
                "You have successFully booked a ticket.Please Find the following details below /n"+
                "Booked seats : "+seats +"/n"
                +"Movie name : "+show.getMovie().getMovieName()+"/n"
                +"Show Date : "+show.getShowDate()+"/n"
                +"Show Time : "+show.getShowTime()+"/n"
                +"Thanks For Visiting us and Enjoy the show !!!";

        simpleMailMessage.setSubject("Ticket Confirmation Mail");
        simpleMailMessage.setFrom("");
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(user.getEmailId());

        emailSender.send(simpleMailMessage);

        return createResponseDto(show,ticket);
    }
    private TicketResponseDto createResponseDto(Show show,Ticket ticket)
    {
        TicketResponseDto ticketResponseDto = TicketResponseDto.builder().bookedSeats(ticket.getBookedSeats())
                .movieName(show.getMovie().getMovieName())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .theatreName(show.getTheatre().getTheatreName())
                .address(show.getTheatre().getAddress())
                .price(ticket.getTotalPrice())
                .build();

        return ticketResponseDto;
    }
}
