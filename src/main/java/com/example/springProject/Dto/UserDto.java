package com.example.springProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 🌟 Ti permette di fare: UserDto.builder().nome("Paolo").build()
public class UserDto {
    private String nome;
    private String Cognome;
    private String email;
}
