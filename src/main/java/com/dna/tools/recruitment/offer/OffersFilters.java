package com.dna.tools.recruitment.offer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OffersFilters {
    private String userName;
    private JobCategory jobCategory;
}
