package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateUserDTO {
    private String login;
    private String password;
    private String name;
    private LocalDate creationDate;
}
