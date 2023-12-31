package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from user where age >= :age ;",nativeQuery = true)
   List<User> findUsersAgeGreater(int age);
}
