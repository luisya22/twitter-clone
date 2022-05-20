package com.luismatos.twitterclonebackend.useraccount.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor @Getter @Setter @AllArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Username is required.")
    @Size(message = "Username should be at least 3 characters long.", min=3)
    private String username;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(message = "Password should be at least 8 characters long.", min=8)
    private String password;
    private String bio;
    private String profilePicturePath;
    private String backgroundPicturePath;
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    Set<Follower> followers;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public User(
            Long id,
            String name,
            String username,
            String email,
            String password,
            String bio,
            String profilePicturePath,
            String backgroundPicturePath,
            LocalDate birthday
    ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.profilePicturePath = profilePicturePath;
        this.backgroundPicturePath = backgroundPicturePath;
        this.birthday = birthday;
    }

    public User(
            String name,
            String username,
            String email,
            String password,
            LocalDate birthday
    ) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public User(Long id, String name, String username, String email, String password, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePicturePath='" + profilePicturePath + '\'' +
                ", backgroundPicturePath='" + backgroundPicturePath + '\'' +
                ", birthday=" + birthday +
                ", followers=" + followers +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(bio, user.bio) && Objects.equals(profilePicturePath, user.profilePicturePath) && Objects.equals(backgroundPicturePath, user.backgroundPicturePath) && Objects.equals(birthday, user.birthday) && Objects.equals(followers, user.followers) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, password, bio, profilePicturePath, backgroundPicturePath, birthday, followers, roles);
    }
}
