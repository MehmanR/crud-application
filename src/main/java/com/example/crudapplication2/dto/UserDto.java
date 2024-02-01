package com.example.crudapplication2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@Builder
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;


}
