package com.dna.tools.recruitment.user;

import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ReadUserDTO> create(@RequestBody final CreateUserDTO request){
        return ResponseEntity.ok(userService.create(request));
    }

    @PutMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> update(@RequestBody final UpdateUserDTO request){
        if (!validateUpdateRequest(request)){
            return ResponseEntity.badRequest().build();
        }
        userService.update(request);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/{login}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public ReadUserDTO get(@PathVariable final String login){
        Preconditions.checkNotNull(login);
        return userService.getUser(login).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with login %s not found", login)));
    }


    @DeleteMapping(value = "/{userId}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Long> delete(@PathVariable final Long userId){
        userService.delete(userId);
        return ResponseEntity.ok(userId);
    }

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public List<ReadUserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    private boolean validateUpdateRequest(UpdateUserDTO request) {
        return !Objects.isNull(request)
                && !Objects.isNull(request.getLogin())
                && !Objects.isNull(request.getName());
    }
}
