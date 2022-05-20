package com.luismatos.twitterclonebackend.useraccount.service;

import com.luismatos.twitterclonebackend.useraccount.dto.UserCreateDto;
import com.luismatos.twitterclonebackend.useraccount.dto.UserResponseDto;

public interface UserService {

    UserResponseDto save(UserCreateDto userCreateDto);
}
