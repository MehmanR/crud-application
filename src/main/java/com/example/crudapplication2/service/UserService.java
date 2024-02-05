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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInter {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> userDtoList = userListToUserDto(allUsers);

        return userDtoList;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            return null;
        }
        return userToUserDto(byId.get());
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

    /*
        private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
     */
    @Override
    public UserDto userToUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return userDto;
    }

    public List<UserDto> userListToUserDto(List<User> user) {
        List<UserDto> userDto = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i) == null) {
                continue;
            }
            UserDto userDto1 = userToUserDto(user.get(i));
            userDto.add(userDto1);
        }


        return userDto;
    }

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
