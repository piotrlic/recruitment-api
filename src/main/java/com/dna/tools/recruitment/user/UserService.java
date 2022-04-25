package com.dna.tools.recruitment.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void create(CreateUserDTO createUserDTO) {
        User user = User.builder()
                .name(createUserDTO.getName())
                .login(createUserDTO.getLogin())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .creationDate(LocalDateTime.now())
                .build();
        userRepository.create(user);
    }

    public void update(UpdateUserDTO request) {
        userRepository.update(request);
    }

    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    public Optional<ReadUserDTO> getUser(final String login) {
        return Optional.ofNullable(userRepository.getUserByLogin(login));
    }

    public Optional<Long> getUserIdByName(final String userName){
        return Optional.ofNullable(userRepository.getUserIdByName(userName));
    }

    public List<ReadUserDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
