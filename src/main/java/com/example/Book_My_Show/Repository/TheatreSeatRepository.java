package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.TheatreSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeat,Integer> {
}
