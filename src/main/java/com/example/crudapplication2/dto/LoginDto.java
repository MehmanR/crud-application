package com.example.crudapplication2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude
public class LoginDto {

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    @NotBlank
    @Email(message = "email duzgun daxil edin!")
    @Size(min = 8, max = 255)
    private String email;
}
