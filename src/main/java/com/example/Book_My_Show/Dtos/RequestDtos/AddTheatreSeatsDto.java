package com.example.Book_My_Show.Dtos.RequestDtos;

import lombok.Data;

@Data
public class AddTheatreSeatsDto {

    private int noOfSeatsIn1Row;

    private int noOfGoldSeats;

    private int noOfSilverSeats;

    private String address;
}
