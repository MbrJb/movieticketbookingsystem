package com.example.movieticketbookingsystem.service;

import com.example.movieticketbookingsystem.converter.UserConverter;
import com.example.movieticketbookingsystem.domain.User;
import com.example.movieticketbookingsystem.dto.UserDto;
import com.example.movieticketbookingsystem.exception.UserNotFoundException;
import com.example.movieticketbookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addNewUser(UserDto userDto){
        User user = UserConverter.convertUserDtoToEntity(userDto);
        user.setName(user.getName());
        userRepository.save(user);

        return UserConverter.convertUserEntityToDto(user);
    }

    public List<UserDto> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserConverter::convertUserEntityToDto)
                .collect(Collectors.toList());
    }

    public void deleteUserById(Long id){
        boolean exists = userRepository.existsById(id);

        if(exists){
            userRepository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("No user with id of " + id + ".");
        }
    }
}
