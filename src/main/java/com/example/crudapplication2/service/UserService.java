package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.RegisterDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.models.User;
import com.example.crudapplication2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements UserServiceInter {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public RegisterDto createUser(RegisterDto userDto) {


        User user = userDtoToUser(userDto);
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);
        userDto.setPassword(null);

        return userDto;
    }

    @Override
    public void updateUserById(Long id, UserDto userDto) {

    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public UserDto makeUserToUserDto(User user) {
        return null;
    }
    /*
     private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDateTime birthDate;
     */

    private User userDtoToUser(RegisterDto userDto) {

        User user = User.builder().
                name(userDto.getName()).
                birthDate(userDto.getBirthDate())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber()).build();

        return user;
    }
}
