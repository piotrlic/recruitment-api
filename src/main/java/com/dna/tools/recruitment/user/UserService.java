package com.dna.tools.recruitment.user;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserService {
    public void create(RequestUserDTO user) {

    }

    public void update(RequestUserDTO request) {
    }

    public void delete(String login) {
    }

    public Optional<UserDTO> getUser(String login) {
        return Optional.of(new UserDTO(login,"Piotr", LocalDateTime.now()));
    }
}
