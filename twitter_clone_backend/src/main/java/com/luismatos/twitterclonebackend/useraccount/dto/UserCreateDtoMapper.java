package com.luismatos.twitterclonebackend.useraccount.dto;

import com.luismatos.twitterclonebackend.useraccount.model.User;
import org.modelmapper.ModelMapper;

public class UserCreateDtoMapper {

    private final ModelMapper modelMapper;

    public UserCreateDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserCreateDto convertToDto(User user){
        return modelMapper.map(user, UserCreateDto.class);
    }

    public User convertToEntity(UserCreateDto userCreateDto){
        return modelMapper.map(userCreateDto, User.class);
    }
}
