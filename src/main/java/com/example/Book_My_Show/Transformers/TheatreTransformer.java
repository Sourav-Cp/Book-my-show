package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.AddTheatreDto;
import com.example.Book_My_Show.Models.Theatre;

public class TheatreTransformer {

    public static Theatre theatreDtoToObj(AddTheatreDto addTheatreDto)
    {
        Theatre theatre = Theatre.builder().theatreName(addTheatreDto.getTheatreName())
                .address(addTheatreDto.getAddress()).build();

        return theatre;
    }
}
