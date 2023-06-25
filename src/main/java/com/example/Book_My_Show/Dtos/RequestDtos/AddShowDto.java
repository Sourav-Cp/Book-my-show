package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AddShowDto {

    private LocalTime showTime;

    private Date showDate;

    private int theatreId;

    private int movieId;
}
