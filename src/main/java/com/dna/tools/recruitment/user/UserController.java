package com.dna.tools.recruitment.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/1/user")
public class UserController {

    @PostMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(CreateUserDTO user){
        System.out.println(user);
    }

    @GetMapping(value = "/{login}", produces = "application/json; charset=UTF-8")
    public Long get(@PathVariable Long login){
        System.out.println("Login:"+login);
        return login;
    }

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("abc","xxx","Piotr", LocalDate.now()));
        return users;
    }
}
