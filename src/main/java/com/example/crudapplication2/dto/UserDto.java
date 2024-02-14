package com.example.crudapplication2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @NotBlank
    @Size(min = 3, max = 30)
    private String surname;

    @NotBlank
    @Email(message = "Email duzgun daxil edin!")
    @Size(min = 8, max = 255)
    private String email;

    @NotBlank
    @Size(min = 12, max = 14)
    @Pattern(regexp = "[+][0-9]{12}", message = "Telefonu duzgun daxil edin")
    private String phoneNumber;

    @NotBlank
    @Past
    private LocalDate birthDate;


    private List<PostDto> postDtoList;


}
