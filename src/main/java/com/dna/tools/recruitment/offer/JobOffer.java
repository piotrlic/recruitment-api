package com.dna.tools.recruitment.offer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class JobOffer {
    private Long id;
    private String userName;
    private JobCategory category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
