package com.example.movieticketbookingsystem.converter;

import com.example.movieticketbookingsystem.domain.User;
import com.example.movieticketbookingsystem.dto.UserDto;

public class UserConverter {

    private UserConverter(){}

    public static UserDto convertUserEntityToDto(User user){

        UserDto userDto = new UserDto();
        userDto.setDtoId(user.getId());
        userDto.setDtoName(user.getName());
        userDto.setDtoBookings(user.getBookings());

        return userDto;
    }

    public static User convertUserDtoToEntity(UserDto userDto){

        User user = new User();
        user.setId(userDto.getDtoId());
        user.setName(userDto.getDtoName());
        user.setBookings(userDto.getDtoBookings());

        return user;
    }
}
