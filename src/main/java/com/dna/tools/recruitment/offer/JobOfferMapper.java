package com.dna.tools.recruitment.offer;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobOfferMapper {

    @Select("SELECT id, user_id as userId, job_category as category, start_date as startDate, end_date as endDate from JOB_OFFERS")
    List<JobOffer> getAllJobOffers();

    @Select("SELECT id, user_id as userId, job_category as category, start_date as startDate, end_date as endDate from JOB_OFFERS where start_date<now() and end_date>now()")
    List<JobOffer> getAllValidOffers();


    @Insert("INSERT INTO JOB_OFFERS (user_id, job_category, start_date, end_date) VALUES (#{userId}, #{category}, #{startDate}, #{endDate})")
    void saveJobOffer(JobOffer jobOffer);
}
