package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Dtos.RequestDtos.AddTheatreDto;
import com.example.Book_My_Show.Dtos.RequestDtos.AddTheatreSeatsDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Models.Theatre;
import com.example.Book_My_Show.Models.TheatreSeat;
import com.example.Book_My_Show.Repository.TheatreRepository;
import com.example.Book_My_Show.Transformers.TheatreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(AddTheatreDto addTheatreDto)
    {
        Theatre theatre = TheatreTransformer.theatreDtoToObj(addTheatreDto);
        theatreRepository.save(theatre);

        return "Theatre Added SuccessFully";
    }

    public String addTheatreSeats(AddTheatreSeatsDto addTheatreSeatsDto)
    {
        int col = addTheatreSeatsDto.getNoOfSeatsIn1Row();

        int goldSeats = addTheatreSeatsDto.getNoOfGoldSeats();
        int silverSeat = addTheatreSeatsDto.getNoOfSilverSeats();

        String location = addTheatreSeatsDto.getAddress();
        Theatre theatre = theatreRepository.findByAddress(location);

        List<TheatreSeat> theatreSeats = theatre.getTheatreSeatList();

        int count = 1;
        char ch = 'A';

        for (int i=0;i<silverSeat;i++)
        {

            String seatNo = count+"";
            seatNo = seatNo+ch;
            ch++;

            if(ch-'A' == col)
            {
                ch = 'A';
                count++;
            }

            TheatreSeat theatreSeat = new TheatreSeat();

            theatreSeat.setSeatType(SeatType.SILVER);
            theatreSeat.setSeatNo(seatNo);
            theatreSeat.setTheatre(theatre);

            theatreSeats.add(theatreSeat);
        }
        for (int i=0;i<goldSeats;i++)
        {

            String seatNo = count+"";
            seatNo = seatNo+ch;
            ch++;

            if(ch-'A' == col)
            {
                ch = 'A';
                count++;
            }

            TheatreSeat theatreSeat = new TheatreSeat();

            theatreSeat.setSeatType(SeatType.GOLD);
            theatreSeat.setSeatNo(seatNo);
            theatreSeat.setTheatre(theatre);

            theatreSeats.add(theatreSeat);
        }
       theatreRepository.save(theatre);

      return "Theatre Seats Added SuccessFully";
        }
}
