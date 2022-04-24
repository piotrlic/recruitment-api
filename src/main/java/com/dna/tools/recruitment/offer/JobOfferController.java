package com.dna.tools.recruitment.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/offers")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService){
        this.jobOfferService = jobOfferService;
    }

    @PostMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(JobOffer jobOffer){
        jobOfferService.create(jobOffer);

    }

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public List<JobOffer> getValidJobOffers(@RequestParam String userName, @RequestParam JobCategory jobCategory){
        validateFilters(userName, jobCategory);

        return jobOfferService.getValidJobOffers(buildFilters(userName, jobCategory));
    }

    private OffersFilters buildFilters(String userName, JobCategory jobCategory) {
        return OffersFilters.builder().userName(userName).jobCategory(jobCategory).build();
    }

    private void validateFilters(String name, JobCategory jobCategory) {

    }
}
