package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Exceptions.NoEntryYetException;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/add")
    public String addUser(@RequestBody AddUserDto addUserDto)
    {
       try
       {
           String res = userService.addUser(addUserDto);
           return res;
       }
       catch (Exception e)
       {
           return e.getMessage();
       }
    }

    @GetMapping("/get-oldest-user")
    public ResponseEntity<UserResponseDto> getOldestUser()
    {
        try {
            UserResponseDto userResponseDto = userService.getOldestUser();
            return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
        }
        catch (NoEntryYetException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/greater-than")
    public List<User> usersGreaterThan(@RequestParam("age")int age)
    {
        List<User> userList = userService.usersGreaterThan(age);
        return userList;
    }
}
