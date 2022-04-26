package com.dna.tools.recruitment.offer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OffersFilters {
    /**
     * this is the class for all filters that may be added on frontend.
     */
    private String userName;
    private JobCategory jobCategory;
}
