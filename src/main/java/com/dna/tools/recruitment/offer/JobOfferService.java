package com.dna.tools.recruitment.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobOfferService {

    private final JobOfferMapper jobOfferMapper;

    public JobOfferService(JobOfferMapper jobOfferMapper) {
        this.jobOfferMapper = jobOfferMapper;
    }

    public void create(JobOffer jobOffer) {
            jobOfferMapper.saveJobOffer(jobOffer);
    }

    public List<JobOffer> getValidJobOffers(OffersFilters offersFilters) {

//        var userId =
        return jobOfferMapper.getAllValidOffers();
    }
}

