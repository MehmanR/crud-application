package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.RegisterDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.models.User;

import java.util.List;

public interface UserServiceInter {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    RegisterDto createUser(RegisterDto userDto);
    void updateUserById(Long id,UserDto userDto);
    void deleteUserById(Long id);

    UserDto userToUserDto(User user);
}
