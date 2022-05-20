package com.luismatos.twitterclonebackend.useraccount.dto;


import com.luismatos.twitterclonebackend.useraccount.model.User;
import org.modelmapper.ModelMapper;

public class UserResponseDtoMapper {

    private final ModelMapper modelMapper;

    public UserResponseDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDto convertToDto(User user){
        return modelMapper.map(user, UserResponseDto.class);
    }

    public User convertToEntity(UserResponseDto userResponseDto){
        return modelMapper.map(userResponseDto, User.class);
    }
}
