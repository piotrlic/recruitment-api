package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserDTO {
    private String login;
    private String name;
}
