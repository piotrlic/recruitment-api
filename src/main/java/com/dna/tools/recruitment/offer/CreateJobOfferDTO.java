package com.dna.tools.recruitment.offer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateJobOfferDTO {
    private Long userId;
    private JobCategory category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /*
     * Other fields like content of the offer or companyName should be added.
     */
}
