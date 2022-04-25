package com.dna.tools.recruitment.offer;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobOfferRepository {

    @Select({"<script>",
                    "SELECT job_offers.id, users.name as userName, job_category as category, start_date as startDate, end_date as endDate from JOB_OFFERS " +
                            " JOIN users on users.id = user_id " +
                    "where start_date &lt; now() and end_date &gt; now()"+
                    "<when test='userId!=null'>" +
                        " AND user_id= #{userId}"+
                    "</when>"+
                    "<when test='jobCategory!=null'>" +
                        " AND job_category= #{jobCategory}"+
                    "</when>"+
            "</script>"}
    )
    List<JobOffer> getAllValidOffers(final Long userId, final JobCategory jobCategory);

    @Insert("INSERT INTO JOB_OFFERS (user_id, job_category, start_date, end_date) VALUES (#{userId}, #{category}, #{startDate}, #{endDate})")
    void saveJobOffer(final CreateJobOfferDTO jobOffer);
}
