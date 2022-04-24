package com.dna.tools.recruitment.offer;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobOfferService {


    public void create(JobOffer jobOffer) {
    }

    public List<JobOffer> getValidJobOffers(OffersFilters offersFilters) {
        return List.of(JobOffer.builder().category(JobCategory.COURIER).startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).userName("Piotrek").build(),
                JobOffer.builder().category(JobCategory.FOOD_AND_DRINKS).startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).userName("Roman").build(),
                JobOffer.builder().category(JobCategory.SHOP_ASSISTANT).startDate(LocalDateTime.now()).endDate(LocalDateTime.now()).userName("Pawel").build()
                );
    }
}

