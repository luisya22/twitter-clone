package com.luismatos.twitterclonebackend.useraccount.service;

import com.luismatos.twitterclonebackend.useraccount.dto.UserCreateDto;
import com.luismatos.twitterclonebackend.useraccount.dto.UserCreateDtoMapper;
import com.luismatos.twitterclonebackend.useraccount.dto.UserResponseDto;
import com.luismatos.twitterclonebackend.useraccount.dto.UserResponseDtoMapper;
import com.luismatos.twitterclonebackend.useraccount.model.User;
import com.luismatos.twitterclonebackend.useraccount.repository.UserRepository;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserCreateDtoMapper userCreateDtoMapper;
    private final UserResponseDtoMapper userResponseDtoMapper;

    public UserServiceImpl(UserRepository userRepository, UserCreateDtoMapper userCreateDtoMapper, UserResponseDtoMapper userResponseDtoMapper) {
        this.userRepository = userRepository;
        this.userCreateDtoMapper = userCreateDtoMapper;
        this.userResponseDtoMapper = userResponseDtoMapper;
    }

    @Override
    public UserResponseDto save(UserCreateDto userCreateDto) {

        User user = userCreateDtoMapper.convertToEntity(userCreateDto);

        User userResponse =  userRepository.save(user);

        return userResponseDtoMapper.convertToDto(userResponse);
    }
}
