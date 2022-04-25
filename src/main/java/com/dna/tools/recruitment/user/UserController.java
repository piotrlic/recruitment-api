package com.dna.tools.recruitment.user;

import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/* TODO
     1. Add all 4 CRUD methods (create, update, delete, get user by login)
     2. Add validation to all the methods.
     3. Prepare responses with errors.
      */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody CreateUserDTO request){

        userService.create(request);
        return ResponseEntity.ok().build();
    }


    @PutMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> update(@RequestBody UpdateUserDTO request){
        validateRequest(request);
            userService.update(request);
            return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/{login}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public ReadUserDTO get(@PathVariable String login){
        Preconditions.checkNotNull(login);
        return userService.getUser(login).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with login %s not found", login)));
    }


    @DeleteMapping(value = "/{userId}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public List<ReadUserDTO> getAllUsers(){

        return userService.getAllUsers();
    }

    private void validateRequest(UpdateUserDTO request) {
        Preconditions.checkNotNull(request);
        Preconditions.checkNotNull(request.getLogin());
        Preconditions.checkNotNull(request.getName());
    }
}
