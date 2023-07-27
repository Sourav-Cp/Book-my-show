package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    public Optional<Movie> findByMovieName(String movieName);
}
