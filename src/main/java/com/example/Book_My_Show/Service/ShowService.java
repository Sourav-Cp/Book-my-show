package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Dtos.RequestDtos.AddShowDto;
import com.example.Book_My_Show.Dtos.RequestDtos.GetAllShowsDto;
import com.example.Book_My_Show.Dtos.RequestDtos.ShowSeatsDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.GetAllShowsResponseDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Exceptions.MovieNotFoundException;
import com.example.Book_My_Show.Exceptions.ShowNotFoundException;
import com.example.Book_My_Show.Exceptions.TheatreNotFoundException;
import com.example.Book_My_Show.Models.*;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheatreRepository;
import com.example.Book_My_Show.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(AddShowDto addShowDto) throws TheatreNotFoundException, MovieNotFoundException
    {
        Show show = ShowTransformer.transformShowDtoToObj(addShowDto);

        Optional<Movie> movieOptional = movieRepository.findById(addShowDto.getMovieId());
        if(movieOptional.isEmpty())
        {
            throw new MovieNotFoundException("Movie of this Id is not Present");
        }

        Optional<Theatre> theatreOptional = theatreRepository.findById(addShowDto.getTheatreId());
        if(theatreOptional.isEmpty())
        {
            throw new TheatreNotFoundException("Theatre of this Id is not Present");
        }

        Movie movie = movieOptional.get();
        Theatre theatre = theatreOptional.get();

        show.setMovie(movie);
        show.setTheatre(theatre);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theatre.getShowList().add(show);

        movieRepository.save(movie);
        theatreRepository.save(theatre);

        return "Show Added SuccessFully";
    }

    public String associateShowSeats(ShowSeatsDto showSeatsDto)throws ShowNotFoundException
    {
        Optional<Show> showOptional = showRepository.findById(showSeatsDto.getShowId());
        if(showOptional.isEmpty())
        {
            throw new ShowNotFoundException("Show of this Id is Unavailable");
        }

        Show show = showOptional.get();

        Theatre theatre = show.getTheatre();
        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();

        List<ShowSeats> showSeatsList = show.getShowSeatsList();

        for(TheatreSeat theatreSeat : theatreSeatList)
        {
            ShowSeats showSeats = new ShowSeats();

            showSeats.setSeatNo(theatreSeat.getSeatNo());
            showSeats.setSeatType(theatreSeat.getSeatType());

            if(showSeats.getSeatType().equals(SeatType.GOLD))
            {
                showSeats.setPrice(showSeatsDto.getGoldSeatPrice());
            }
            else showSeats.setPrice(showSeatsDto.getSilverSeatPrice());

            showSeats.setShow(show);

            showSeats.setAvailable(true);
            showSeats.setFoodAttached(false);

            showSeatsList.add(showSeats);
        }
        showRepository.save(show);

        return "Show Seats has been SuccessFully Associated";
    }
    public List<GetAllShowsResponseDto> getAllShowsOfMovieInTheatre(GetAllShowsDto getAllShowsDto)
    {
        List<Integer> showsIdList = showRepository.getAllShows(getAllShowsDto.getShowDate(),getAllShowsDto.getTheatreId(),getAllShowsDto.getMovieId());
        List<GetAllShowsResponseDto> shows = new ArrayList<>();

        for(Integer ids : showsIdList)
        {
            Show show = showRepository.findById(ids).get();
            GetAllShowsResponseDto getAllShowsResponseDto = ShowTransformer.transformToObj(show);
            shows.add(getAllShowsResponseDto);
        }
        return shows;
    }
}
