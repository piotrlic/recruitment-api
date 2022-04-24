package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private String login;
    private String name;
    private LocalDateTime creationDate;
}
