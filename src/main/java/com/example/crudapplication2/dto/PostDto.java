package com.example.crudapplication2.dto;

import com.example.crudapplication2.models.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@JsonInclude()
public class PostDto {

    @Size(max = 255)
    private String description;

    private User user;

}
