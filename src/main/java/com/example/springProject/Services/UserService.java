package com.example.springProject.Services;

import com.example.springProject.Dto.UserDto;
import com.example.springProject.Entity.User;
import com.example.springProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping; // 🌟 IMPORTANTE: serve per il @GetMapping

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUser() {
        List<User> utentiDalDb = userRepository.findAll();
        return utentiDalDb.stream().map(user -> UserDto.builder()
                .nome(user.getNome()).email(user.getEmail())
                .build()
        ).collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        Optional<User> utenteDalDb = userRepository.findById(id);

        if (utenteDalDb.isPresent()) {
            User user = utenteDalDb.get();
            return UserDto.builder()
                    .nome(user.getNome())
                    .email(user.getEmail())
                    .build();
        } else {
            // Se l'ID non esiste sul DB, lanciamo un'eccezione
            throw new RuntimeException("Utente non trovato con ID: " + id);
        }
    }
}

