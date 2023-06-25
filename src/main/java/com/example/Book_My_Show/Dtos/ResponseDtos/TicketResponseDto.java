package com.example.Book_My_Show.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {

    private LocalTime showTime;
    private Date showDate;

    private String movieName;

    private String theatreName;

    private String bookedSeats;

    private String address;

    private int price;
}
