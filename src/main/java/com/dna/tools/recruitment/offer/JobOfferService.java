package com.dna.tools.recruitment.offer;

import com.dna.tools.recruitment.user.UserService;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    private final UserService userService;

    public JobOfferService(final JobOfferRepository jobOfferRepository, final UserService userService) {
        this.jobOfferRepository = jobOfferRepository;
        this.userService = userService;
    }

    public CreateJobOfferDTO create(final CreateJobOfferDTO jobOffer) {
            jobOfferRepository.saveJobOffer(jobOffer);
            return jobOffer;
    }

    public List<JobOffer> getValidJobOffers(final OffersFilters offersFilters) {

        var userName = offersFilters.getUserName();
        if (Strings.isEmpty(userName)){
            return jobOfferRepository.getAllValidOffers(null, offersFilters.getJobCategory());
        } else {
            return tryToFindUserWithGivenName(userName)
                    .map(id -> jobOfferRepository.getAllValidOffers(id, offersFilters.getJobCategory()))
                    .orElse(List.of());
        }
    }

    private Optional<Long> tryToFindUserWithGivenName(String userName) {
        return userService.getUserIdByName(userName);
    }
}

