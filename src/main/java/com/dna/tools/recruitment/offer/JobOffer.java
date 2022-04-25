package com.dna.tools.recruitment.offer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class JobOffer {
    private Long id;
    private Long userId;
    private JobCategory category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
