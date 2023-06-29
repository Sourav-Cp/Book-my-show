package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.AddShowDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.GetAllShowsResponseDto;
import com.example.Book_My_Show.Models.Show;

public class ShowTransformer {

    public static Show transformShowDtoToObj(AddShowDto addShowDto)
    {
        Show show = Show.builder().showTime(addShowDto.getShowTime())
                .showDate(addShowDto.getShowDate()).build();

        return show;
    }
    public static GetAllShowsResponseDto transformToObj(Show show)
    {
        GetAllShowsResponseDto getAllShowsResponseDto = GetAllShowsResponseDto.builder()
                .showTime(show.getShowTime())
                .movieName(show.getMovie().getMovieName())
                .build();

        return getAllShowsResponseDto;
    }
}
