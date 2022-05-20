package com.luismatos.twitterclonebackend.useraccount.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class UserCreateDto {

    private String name;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday;
}
