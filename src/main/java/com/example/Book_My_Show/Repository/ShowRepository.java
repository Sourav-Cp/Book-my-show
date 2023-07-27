package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Dtos.RequestDtos.GetAllShowsDto;
import com.example.Book_My_Show.Dtos.RequestDtos.GetShowTimeDto;
import com.example.Book_My_Show.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

    @Query(value = "select id from shows where movie_movie_id = :movieId and theatre_theatre_id = :theatreId and show_date = :showDate ;",nativeQuery = true)
    public List<Integer> getAllShows(String showDate ,int theatreId,int movieId);

    @Query(value = "select movie_movie_id from shows where show_date = :atDate group by movie_movie_id order by count(movie_movie_id) desc limit 1 ;",nativeQuery = true)
    public int getMovieWithMostShow(String atDate);

    @Query(value = "select show_time from shows where movie_movie_id = :movieId and theatre_theatre_id = :theatreId ;",nativeQuery = true)
    public List<String> getShowTime(int movieId,int theatreId);
}
