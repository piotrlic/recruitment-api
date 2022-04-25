package com.dna.tools.recruitment.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReadUserDTO {
    private Long id;
    private String login;
    private String name;
    private LocalDateTime creationDate;
}
