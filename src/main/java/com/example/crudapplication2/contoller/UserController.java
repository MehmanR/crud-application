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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        UserDto userById = userServicer.getUserById(id);
        if (userById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userById);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userServicer.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable(name = "id") Long id) {
        userServicer.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
