package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class ShowSeatsDto {

    private int showId;

    private int goldSeatPrice;

    private int silverSeatPrice;
}
