package com.example.crudapplication2.contoller;

import com.example.crudapplication2.dto.RegisterDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.models.User;
import com.example.crudapplication2.service.UserServiceInter;
import jakarta.validation.Valid;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserServiceInter userServicer;

    public UserController(UserServiceInter userService) {
        this.userServicer = userService;
    }

    @PostMapping()
    public ResponseEntity<RegisterDto> createUser(@Valid @RequestBody RegisterDto userDto) {

        RegisterDto user = userServicer.createUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }




}
