package com.dna.tools.recruitment.user;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


public class UserControllerTest {

    public static final String TEST_LOGIN = "test";
    public static final String TEST_NAME = "name";
    public static final String TEST_PASSWORD = "xxx";
    private static final String NEW_NAME = "newName";
    private static final Long TEST_USER_ID=314L;
    private static final LocalDateTime TEST_CREATION_DATE = LocalDateTime.now();
    private static final ReadUserDTO TEST_USER = ReadUserDTO.builder().id(TEST_USER_ID).login(TEST_LOGIN)
            .name(TEST_NAME).creationDate(TEST_CREATION_DATE).build();

    @Test
    public void shouldCreateUserWithStatusOk(){
        //Given
        var userRepository = Mockito.mock(UserRepository.class);
        var passWordEncoder = Mockito.mock(PasswordEncoder.class);
        var userService = new UserService(userRepository, passWordEncoder);
        var userController = new UserController(userService);
        CreateUserDTO user = CreateUserDTO.builder()
                .login(TEST_LOGIN)
                .name(TEST_NAME)
                .password(TEST_PASSWORD)
                .build();
        //When
        var response = userController.create(user);

        //Then
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        var body = response.getBody();
        Assert.assertNotNull(body);
        Assert.assertEquals(TEST_LOGIN, body.getLogin());
        Assert.assertEquals(TEST_NAME, body.getName());
        Assert.assertNotNull(body.getCreationDate());
        Mockito.verify(passWordEncoder).encode(TEST_PASSWORD);
        Mockito.verify(userRepository).create(Mockito.any());
    }

    @Test
    public void shouldUpdateUserWithStatusOk(){
        //Given
        var userRepository = Mockito.mock(UserRepository.class);
        var passWordEncoder = Mockito.mock(PasswordEncoder.class);
        var userService = new UserService(userRepository, passWordEncoder);
        var userController = new UserController(userService);
        UpdateUserDTO user = UpdateUserDTO.builder()
                .login(TEST_LOGIN)
                .name(NEW_NAME)
                .build();
        //When
        var response = userController.update(user);

        //Then
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(userRepository).update(user);
    }

    @Test
    public void shouldDeleteUserWithStatusOk(){
        //Given
        var userRepository = Mockito.mock(UserRepository.class);
        var passWordEncoder = Mockito.mock(PasswordEncoder.class);
        var userService = new UserService(userRepository, passWordEncoder);
        var userController = new UserController(userService);
        //When
        var response = userController.delete(TEST_USER_ID);

        //Then
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(userRepository).delete(TEST_USER_ID);
        var body = response.getBody();
        Assert.assertNotNull(body);
        Assert.assertEquals(TEST_USER_ID, body);
    }

    @Test
    public void shouldGetUserWithStatusOk(){
        //Given
        var userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.getUserByLogin(TEST_LOGIN)).thenReturn(TEST_USER);
        var passWordEncoder = Mockito.mock(PasswordEncoder.class);
        var userService = new UserService(userRepository, passWordEncoder);
        var userController = new UserController(userService);
        //When
        var response = userController.get(TEST_LOGIN);

        //Then
        Mockito.verify(userRepository).getUserByLogin(TEST_LOGIN);
        Assert.assertNotNull(response);
        Assert.assertEquals(TEST_LOGIN, response.getLogin());
        Assert.assertEquals(TEST_NAME, response.getName());
        Assert.assertEquals(TEST_CREATION_DATE, response.getCreationDate());
    }

    @Test
    public void shouldReturnBadRequestWhenNoLogin(){
        UpdateUserDTO user = UpdateUserDTO.builder()
                .name(NEW_NAME)
                .build();
        callUpdateAndExpectBadRequest(user);
    }

    @Test
    public void shouldNotUpdateAndReturnBadRequestWhenNoUserName(){
        UpdateUserDTO user = UpdateUserDTO.builder()
                .login(TEST_LOGIN)
                .build();
        callUpdateAndExpectBadRequest(user);
    }

    private void callUpdateAndExpectBadRequest(final UpdateUserDTO user) {
        //Given
        var userRepository = Mockito.mock(UserRepository.class);
        var passWordEncoder = Mockito.mock(PasswordEncoder.class);
        var userService = new UserService(userRepository, passWordEncoder);
        var userController = new UserController(userService);

        //When
        var response = userController.update(user);

        //Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Mockito.verifyNoInteractions(userRepository);
    }
}