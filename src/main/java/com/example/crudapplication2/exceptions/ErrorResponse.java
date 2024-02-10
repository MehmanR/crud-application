package com.example.crudapplication2.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus status;

    private LocalDateTime time;


}
