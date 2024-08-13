package com.example.movieticketbookingsystem.controller;

import com.example.movieticketbookingsystem.dto.UserDto;
import com.example.movieticketbookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public UserDto addNewUser(@RequestBody UserDto userDto){
        return userService.addNewUser(userDto);
    }

    @GetMapping
    public List<UserDto> findAllUsers(){
        return userService.findAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
