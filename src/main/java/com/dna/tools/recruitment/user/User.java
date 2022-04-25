package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class User {
    private String login;
    private String password;
    private String name;
    private LocalDateTime creationDate;
}
