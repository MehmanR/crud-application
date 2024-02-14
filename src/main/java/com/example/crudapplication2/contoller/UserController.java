package com.example.crudapplication2.contoller;

import com.example.crudapplication2.dto.RegisterDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.service.UserServiceInter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@Validated
public class UserController {

    private final UserServiceInter userService;

    public UserController(UserServiceInter userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<RegisterDto> createUser(@Valid @RequestBody RegisterDto userDto) {

        RegisterDto user = userService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@Positive @PathVariable(name = "id") Long id) {
        UserDto userById = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(userById);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable(name = "id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserById(@Valid @PathVariable(name = "id") Long id , @RequestBody UserDto userDto) {

        userService.updateUserById(id,userDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
