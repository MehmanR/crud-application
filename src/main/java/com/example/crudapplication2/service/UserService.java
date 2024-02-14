package com.example.crudapplication2.service;

import com.example.crudapplication2.dto.RegisterDto;
import com.example.crudapplication2.dto.UserDto;
import com.example.crudapplication2.exceptions.UserNotFoundException;
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

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> userDtoList = userListToUserDto(allUsers);

        return userDtoList;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id: " + id));

        return userToUserDto(user);
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
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            System.out.println("User not found by id: " + id);
        }
        User user = byId.get();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(user.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setBirthDate(userDto.getBirthDate());
        user.setUpdateDate(LocalDateTime.now());
        userRepository.save(user);

    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.deleteById(id);
        } else {
            System.out.println("User not found by id:" + id);
        }


    }

    /*
        private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
     */

    public static UserDto userToUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return userDto;
    }

    public static User userDtoToUser(UserDto userDto){
        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .birthDate( userDto.getBirthDate())
                .phoneNumber(userDto.getPhoneNumber())
                .posts( PostService.ListPostDtoToPostList(userDto.getPostDtoList()))
                .build();

        return user;
    }

    public static List<UserDto> userListToUserDto(List<User> user) {
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
