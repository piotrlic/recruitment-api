package com.dna.tools.recruitment.user;

import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* TODO
     1. Add all 4 CRUD methods (create, update, delete, get user by login)
     2. Add validation to all the methods.
     3. Prepare responses with errors.
     4. Add
      */
@RestController
@RequestMapping("/1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(CreateUserDTO request){
        validateRequest(request);
        userService.create(request);
    }


    @PutMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(UpdateUserDTO request){
       // validateRequest(request);
        userService.update(request);
    }

    @GetMapping(value = "/{login}", produces = "application/json; charset=UTF-8")
    public UserDTO get(@PathVariable String login){

        return (new UserDTO(login,"Piotr", LocalDateTime.now()));
    }


    @DeleteMapping(value = "/{login}", produces = "application/json; charset=UTF-8")
    public void delete(@PathVariable String login){

        userService.delete(login);
    }

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public List<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("abc","Piotr", LocalDateTime.now()));
        return users;
    }

    private void validateRequest(CreateUserDTO request) {
        Preconditions.checkNotNull(request);
        Preconditions.checkNotNull(request.getLogin());
        Preconditions.checkNotNull(request.getPassword());
    }
}
