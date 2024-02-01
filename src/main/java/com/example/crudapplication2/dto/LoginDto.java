package com.example.crudapplication2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    private String password;
    private String email;
}
