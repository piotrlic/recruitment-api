package com.dna.tools.recruitment.user;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserControllerTest {

    public static final String TEST_LOGIN = "test";
    public static final String TEST_NAME = "name";
    public static final String TEST_PASSWORD = "xxx";
    private static final String NEW_NAME = "newName";

    @Test
    public void shouldCreateUserWIthStatusOk(){
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
        Mockito.verify(passWordEncoder).encode(TEST_PASSWORD);
        Mockito.verify(userRepository).create(Mockito.any());
    }

    @Test
    public void shouldUpdateUserWIthStatusOk(){
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