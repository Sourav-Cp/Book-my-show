package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Exceptions.NoEntryYetException;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(AddUserDto addUserDto)
    {
        User user = UserTransformer.transformDtoToObj(addUserDto);
        userRepository.save(user);

        return "User Added SuccessFully";
    }
    public UserResponseDto getOldestUser() throws NoEntryYetException
    {
        List<User> userList = userRepository.findAll();
        int maxAge = 0;
        User user = null;

        for (User user1 : userList)
        {
            if(user1.getAge()>maxAge)
            {
                maxAge=user1.getAge();
                user = user1;
            }
        }
        if(user == null)
        {
            throw new NoEntryYetException("There is no Entry in database");
        }
        UserResponseDto userResponseDto = UserTransformer.transformObjToUser(user);
        return userResponseDto;
    }

    public List<User> usersGreaterThan(int age)
    {
        List<User> userList = userRepository.findUsersAgeGreater(age);
        return userList;
    }
}
