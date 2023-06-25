package com.example.Book_My_Show.Transformers;

import com.example.Book_My_Show.Dtos.RequestDtos.AddUserDto;
import com.example.Book_My_Show.Dtos.ResponseDtos.UserResponseDto;
import com.example.Book_My_Show.Models.User;

public class UserTransformer {
    public static User transformDtoToObj(AddUserDto addUserDto)
    {
        User user = User.builder().age(addUserDto.getAge())
                .name(addUserDto.getName())
                .mobNo(addUserDto.getMobNo())
                .emailId(addUserDto.getEmailId())
                .build();

        return user;
    }
    public static UserResponseDto transformObjToUser(User user)
    {
        UserResponseDto userResponseDto = UserResponseDto.builder().age(user.getAge())
                .mobNo(user.getMobNo())
                .name(user.getName())
                .build();

        return userResponseDto;
    }
}
