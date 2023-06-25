package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
    Theatre findByAddress(String address);
}
