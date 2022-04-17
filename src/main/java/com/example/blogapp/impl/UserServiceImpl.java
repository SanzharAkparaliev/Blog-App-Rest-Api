package com.example.blogapp.impl;

import com.example.blogapp.entities.User;
import com.example.blogapp.exceptions.ResourseNotFoundException;
import com.example.blogapp.payloads.UserDto;
import com.example.blogapp.repositories.UserRepository;
import com.example.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User saveUser = userRepository.save(user);
        return userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
     User user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User"," Id ",userId));

     user.setName(userDto.getName());
     user.setEmail(userDto.getEmail());
     user.setAbout(userDto.getAbout());
     user.setPassword(userDto.getPassword());

     User updatedUser = userRepository.save(user);
     UserDto userDto1 =  userToDto(updatedUser);
     return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
       User user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User"," Id ",userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users = userRepository.findAll();
       List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User"," Id ",userId));
        userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
