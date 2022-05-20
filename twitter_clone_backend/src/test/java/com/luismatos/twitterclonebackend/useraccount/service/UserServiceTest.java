package com.luismatos.twitterclonebackend.useraccount.service;

import com.luismatos.twitterclonebackend.useraccount.dto.UserCreateDto;
import com.luismatos.twitterclonebackend.useraccount.dto.UserCreateDtoMapper;
import com.luismatos.twitterclonebackend.useraccount.dto.UserResponseDto;
import com.luismatos.twitterclonebackend.useraccount.dto.UserResponseDtoMapper;
import com.luismatos.twitterclonebackend.useraccount.model.User;
import com.luismatos.twitterclonebackend.useraccount.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserCreateDtoMapper userCreateDtoMapper;
    @Mock
    private UserResponseDtoMapper userResponseDtoMapper;
    @InjectMocks
    private UserServiceImpl underTest;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    private ArgumentCaptor<UserCreateDto> userCreateDtoArgumentCaptor;
    @Captor
    private ArgumentCaptor<UserResponseDto> userResponseDtoArgumentCaptor;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    // Can register account all info
    @Test
    @Disabled
    void canRegisterAccount(){
        // given
        String name = "John Doe";
        String username = "jdoe";
        String email = "jdoe@email.com";
        String password = "test1234";
        LocalDate birthday = LocalDate.of(1988, 2, 13);


        UserCreateDto userCreateDto = new UserCreateDto(name, username, email, password, birthday);
        User user = new User(name, username, email, password, birthday);

        // Convert to Entity would return user
        given(userCreateDtoMapper.convertToEntity(userCreateDto))
                .willReturn(user);

        // Database would set an auto-generated Long id and return the user
        Long databaseGeneratedId = 1L;

        User userDBResponse = new User(
                name,
                username,
                email,
                password,
                birthday
        );

        userDBResponse.setId(databaseGeneratedId);

        given(userRepository.save(user))
                .willReturn(userDBResponse);

        // Convert to ResponseDto
        UserResponseDto expectedResponse = new UserResponseDto(
                databaseGeneratedId,
                name,
                username,
                email,
                birthday
        );

        given(userResponseDtoMapper.convertToDto(userDBResponse))
                .willReturn(expectedResponse);


        // when
        UserResponseDto responseUser = underTest.save(userCreateDto);


        // then

        // check that the correct info is passed to createDtoMapper
        then(userCreateDtoMapper).should().convertToEntity(userCreateDtoArgumentCaptor.capture());
        UserCreateDto userCreateDtoArgumentCaptorValue = userCreateDtoArgumentCaptor.getValue();
        assertThat(userCreateDtoArgumentCaptorValue)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "username", "email", "password", "birthday")
                .isEqualTo(userCreateDto);

        // Check that the correct info is passed to repository
        then(userRepository).should().save(userArgumentCaptor.capture());
        User userArgumentCaptorValue = userArgumentCaptor.getValue();
        assertThat(userArgumentCaptorValue)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "username", "email", "password", "birthday")
                .isEqualTo(
                        new User(name, username, email, password, birthday)
                );

        // Check that the correct info is passed to updateDtoMapper
        then(userResponseDtoMapper).should().convertToDto(userArgumentCaptor.capture());
        User userResponseDtoArgumentCaptorValue = userArgumentCaptor.getValue();
        assertThat(userResponseDtoArgumentCaptorValue)
                .usingRecursiveComparison()
                .isEqualTo(
                        new User(databaseGeneratedId, name, username, email, password, birthday)
                );

        // Check that the response is correct
        assertThat(responseUser)
                .isEqualTo(
                        new UserResponseDto(databaseGeneratedId, name, username, email, birthday)
                );

        assertThat(responseUser.getId()).isNotNull();
    }

    // Can update account
    // save throws exception if name is empty
    // save throws exception if username is empty
    // save throws exception if password is empty
    // save throws exception if username is shorter than 3 characters
    // save throws exception if password is shorter than 8 characters
    // find returns user
    // find id null returns exception not found
    // password is encrypted
}
