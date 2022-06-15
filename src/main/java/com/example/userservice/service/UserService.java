package com.example.userservice.service;

import com.example.userservice.controller.dto.UserDto;
import com.example.userservice.controller.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
