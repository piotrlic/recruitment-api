package com.dna.tools.recruitment.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    public static final String TEST_LOGIN = "test";
    public static final String TEST_NAME = "name";
    public static final String TEST_PASSWORD = "xxx";

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
    }
}