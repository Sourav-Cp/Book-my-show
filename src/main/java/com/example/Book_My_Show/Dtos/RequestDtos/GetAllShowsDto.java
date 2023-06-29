package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class GetAllShowsDto {

    private int movieId;

    private int theatreId;

    private String showDate;
}
