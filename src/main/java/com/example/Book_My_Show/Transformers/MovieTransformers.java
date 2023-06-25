package com.example.Book_My_Show.Transformers;


import com.example.Book_My_Show.Dtos.RequestDtos.MovieEntryDto;
import com.example.Book_My_Show.Models.Movie;

public class MovieTransformers {

    public static Movie transformMovieDToToObj(MovieEntryDto movieEntryDto)
    {
        Movie movie = Movie.builder().movieName(movieEntryDto.getMovieName())
                .genre(movieEntryDto.getGenre())
                .rating(movieEntryDto.getRating())
                .duration(movieEntryDto.getDuration())
                .language(movieEntryDto.getLanguage())
                .releaseDate(movieEntryDto.getReleaseDate())
                .build();

        return movie;
    }
}
