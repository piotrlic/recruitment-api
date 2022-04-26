package com.dna.tools.recruitment.offer;

import com.dna.tools.recruitment.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RunWith(SpringRunner.class)
public class JobOfferControllerTest {

    private static final List<JobOffer> ALL_JOB_OFFERS =
            List.of(JobOffer.builder().id(1L).build(), JobOffer.builder().id(2L).build());
    private static final String DUMMY_USER_NAME = "userNameDummy";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void shouldCreateJobOffer(){
        //Given
        JobOfferController jobOfferController = setupController();

        //When
        var jobOffer = CreateJobOfferDTO.builder()
                .category(JobCategory.IT)
                .startDate(LocalDateTime.parse("2022-02-01 00:10", FORMATTER))
                .endDate(LocalDateTime.parse("2022-02-01 00:10", FORMATTER))
                .userId(1L)
                .build();

        var response = jobOfferController.create(jobOffer);

        //Then
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        var body = response.getBody();
        Assert.assertNotNull(body);
    }

    @Test
    public void shouldReturnBadRequestWhenNoFieldsInRequest(){
        //Given
        JobOfferController jobOfferController = setupController();

        //When
        var jobOffer = CreateJobOfferDTO.builder()
                .build();
        var response = jobOfferController.create(jobOffer);

        //Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenDatesAreMissingInRequest(){
        //Given
        JobOfferController jobOfferController = setupController();

        //When
        var jobOffer = CreateJobOfferDTO.builder()
                .category(JobCategory.IT)
                .userId(1L)
                .build();

        var response = jobOfferController.create(jobOffer);

        //Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldReturnAllJobOffers(){
        //Given
        var jobOfferRepository = Mockito.mock(JobOfferRepository.class);
        Mockito.when(jobOfferRepository.getAllValidOffers(null, null)).thenReturn(ALL_JOB_OFFERS);
        JobOfferController jobOfferController = setupController(jobOfferRepository);

        //When
        var response = jobOfferController.getValidJobOffers(null, null);

        //Then
        Assert.assertEquals(ALL_JOB_OFFERS, response);
    }

    @Test
    public void shouldReturnEmptyListWhenUserNotFound(){
        //Given
        var jobOfferRepository = Mockito.mock(JobOfferRepository.class);
        var userService = Mockito.mock(UserService.class);
        JobOfferController jobOfferController = setupController(jobOfferRepository, userService);
        Mockito.when(jobOfferRepository.getAllValidOffers(null, null)).thenReturn(ALL_JOB_OFFERS);
        //When
        var response = jobOfferController.getValidJobOffers(DUMMY_USER_NAME, null);

        //Then
        Assert.assertTrue(response.isEmpty());
    }

    private JobOfferController setupController(final JobOfferRepository jobOfferRepository, final UserService userService) {
        var jobOfferService = new JobOfferService(jobOfferRepository, userService);
        return new JobOfferController(jobOfferService);
    }

    private JobOfferController setupController(final JobOfferRepository jobOfferRepository){
        var userService = Mockito.mock(UserService.class);
        return setupController(jobOfferRepository, userService);
    }

    private JobOfferController setupController() {
        var jobOfferRepository = Mockito.mock(JobOfferRepository.class);
        return setupController(jobOfferRepository);
    }

}