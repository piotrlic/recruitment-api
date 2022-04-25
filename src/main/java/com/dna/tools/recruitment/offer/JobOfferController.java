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
    public void create(@RequestBody final CreateJobOfferDTO jobOffer){
        jobOfferService.create(jobOffer);
    }

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public List<JobOffer> getValidJobOffers(@RequestParam(required=false) final String userName,
                                            @RequestParam(required=false) final JobCategory jobCategory){
        return jobOfferService.getValidJobOffers(buildFilters(userName, jobCategory));
    }

    private OffersFilters buildFilters(final String userName, final JobCategory jobCategory) {
        return OffersFilters.builder().userName(userName).jobCategory(jobCategory).build();
    }
}
