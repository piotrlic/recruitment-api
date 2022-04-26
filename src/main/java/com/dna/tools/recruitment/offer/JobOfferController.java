package com.dna.tools.recruitment.offer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/offers")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService){
        this.jobOfferService = jobOfferService;
    }

    @PostMapping(consumes = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<CreateJobOfferDTO> create(@RequestBody final CreateJobOfferDTO jobOffer){
        //user is provided with jobOffer entity but when we have authentication we could use already authenticated user.
        if (!validateJobOffer(jobOffer)){
            return ResponseEntity.badRequest().build();
        }
        jobOfferService.create(jobOffer);
        return ResponseEntity.ok(jobOffer);
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(code = HttpStatus.OK)
    public List<JobOffer> getValidJobOffers(@RequestParam(required=false) final String userName,
                                            @RequestParam(required=false) final JobCategory jobCategory){
        return jobOfferService.getValidJobOffers(buildFilters(userName, jobCategory));
    }

    private boolean validateJobOffer(CreateJobOfferDTO jobOffer) {
        return !Objects.isNull(jobOffer)
                && !Objects.isNull(jobOffer.getUserId())
                && !Objects.isNull(jobOffer.getCategory())
                && !Objects.isNull(jobOffer.getStartDate())
                && !Objects.isNull(jobOffer.getEndDate());
    }

    private OffersFilters buildFilters(final String userName, final JobCategory jobCategory) {
        return OffersFilters.builder().userName(userName).jobCategory(jobCategory).build();
    }
}
