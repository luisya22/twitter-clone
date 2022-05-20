package com.luismatos.twitterclonebackend.useraccount.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor @Getter @Setter @AllArgsConstructor
@ToString
public class UserResponseDto {


    private Long id;
    private String name;
    private String username;
    private String email;
    private String bio;
    private String profilePicturePath;
    private String backgroundPicturePath;
    private LocalDate birthday;

    public UserResponseDto(Long id, String name, String username, String email, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return id.equals(that.id) && name.equals(that.name) && username.equals(that.username) && email.equals(that.email) && Objects.equals(bio, that.bio) && Objects.equals(profilePicturePath, that.profilePicturePath) && Objects.equals(backgroundPicturePath, that.backgroundPicturePath) && birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, bio, profilePicturePath, backgroundPicturePath, birthday);
    }
}
