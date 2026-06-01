package com.example.springProject.Controller;

import com.example.springProject.Dto.UserDto;
import com.example.springProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUser();
    }
    @GetMapping("/utente/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
