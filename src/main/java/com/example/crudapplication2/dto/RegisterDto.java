package com.example.crudapplication2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Setter
@Getter
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RegisterDto {


    @NotBlank
    @Size(min = 3,max = 30)
    private String name;

    @NotBlank
    @Size(min = 3, max = 30)
    private String surname;

    @NotBlank
    @Email(message = "Email yalnışdır")
    @Size(min = 8,max = 255)
    private String email;

    @NotBlank
    @Size(min = 8,max = 255)
    private String password;

    @JsonProperty(value = "phone")
    @NotBlank
    @Size(min = 12,max = 14)
    @Pattern(regexp = "[+][0-9]{12}", message = "Telefon nomresi yalnışdır!")
    private String phoneNumber;

    @JsonProperty(value = "birthday")
    @Past
    private LocalDate birthDate;

}
