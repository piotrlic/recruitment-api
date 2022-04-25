package com.dna.tools.recruitment.offer;

import com.dna.tools.recruitment.offer.exception.UserNotFoundException;
import com.dna.tools.recruitment.user.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    private final UserService userService;

    public JobOfferService(final JobOfferRepository jobOfferRepository, final UserService userService) {
        this.jobOfferRepository = jobOfferRepository;
        this.userService = userService;
    }

    public void create(final CreateJobOfferDTO jobOffer) {
            jobOfferRepository.saveJobOffer(jobOffer);
    }

    public List<JobOffer> getValidJobOffers(final OffersFilters offersFilters) {

        return userService.getUserIdByName(offersFilters.getUserName())
                .map(id -> jobOfferRepository.getAllValidOffers(id, offersFilters.getJobCategory()))
                .orElse(List.of());
    }
}

