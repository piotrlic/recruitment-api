package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {
    private String login;
    private String name;
    private String password;
}
