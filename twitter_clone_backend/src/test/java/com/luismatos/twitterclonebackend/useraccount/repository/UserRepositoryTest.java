package com.luismatos.twitterclonebackend.useraccount.repository;

import com.luismatos.twitterclonebackend.useraccount.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldInsertUser() {
        // Given
        Long id = 1L;
        String name = "John Doe";
        String username = "jdoe";
        String email = "jdoe@email.com";
        String password = "test1234";
        String bio = "This is the bio";
        String profilePicturePath = "/path";
        String backgroundPicturePath = "/backgroundPath";
        LocalDate birthday = LocalDate.of(1988, 2, 13);

        User user = new User(id, name, username, email, password, bio, profilePicturePath, backgroundPicturePath, birthday);
        // When
        underTest.save(user);

        // Then
        Optional<User> userOptional = underTest.findById(id);
        assertThat(userOptional)
                .isPresent();

        assertThat(userOptional.get())
                .usingRecursiveComparison()
                .isEqualTo(user);
    }

    @Test
    void itShouldfindByUsername() {
        // Given
        Long id = 1L;
        String name = "John Doe";
        String username = "jdoe";
        String email = "jdoe@email.com";
        String password = "test1234";
        String bio = "This is the bio";
        String profilePicturePath = "/path";
        String backgroundPicturePath = "/backgroundPath";
        LocalDate birthday = LocalDate.of(1988, 2, 13);

        User user = new User(id, name, username, email, password, bio, profilePicturePath, backgroundPicturePath, birthday);
        underTest.save(user);

        // When
        Optional<User> userOptional = underTest.findByUsername(username);
        // Then

        assertThat(userOptional)
                .isPresent();

        assertThat(userOptional.get())
                .usingRecursiveComparison()
                .isEqualTo(user);
    }

    // save throws exception if name is empty

    @Test
    void itShouldThrowIfNameEmpty() {
        // Given
        Long id = 1L;
        String name = null;
        String username = "jdoe";
        String email = "jdoe@email.com";
        String password = "test1234";
        String bio = "This is the bio";
        String profilePicturePath = "/path";
        String backgroundPicturePath = "/backgroundPath";
        LocalDate birthday = LocalDate.of(1988, 2, 13);

        User user = new User(id, name, username, email, password, bio, profilePicturePath, backgroundPicturePath, birthday);

        // When
        // Then
        assertThatThrownBy(() -> underTest.save(user))
                .isInstanceOf(DataIntegrityViolationException.class);

    }

    // save throws exception if username is empty
    // save throws exception if password is empty
    // save throws exception if username is shorter than 3 characters
    // save throws exception if password is shorter than 8 characters
}