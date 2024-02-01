package com.example.crudapplication2.dto;

import com.example.crudapplication2.models.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder
public class PostDto {
    private Long id;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
    private User user;
    private int updateCount;
}
