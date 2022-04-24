package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestUserDTO {
    private String login;
    private String password;
    private String name;
}
