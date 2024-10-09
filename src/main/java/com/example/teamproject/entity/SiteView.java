package com.example.teamproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "SiteView")
@Getter
@Setter
public class SiteView {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "MAIN_TEXT")
    private String mainText;

    @Lob
    @Column(name = "Moving_Companies_Ratings_and_Reviews")
    private String movingCompaniesRatingsAndReviews;

    @Lob
    @Column(name = "What_is_a_Moving_Service?")
    private String whatIsAMovingService;

    @Lob
    @Column(name = "Moving_Tips_And_Tricks_1")
    private String movingTipsAndTricks1;

    @Lob
    @Column(name = "Moving_Tips_And_Tricks_2")
    private String movingTipsAndTricks2;

    @Column(name = "LOCATIONS_STAT")
    private String locationsStat;

    @Column(name = "MOVING_COMPANY_STAT")
    private String movingCompanyStat;

    @Column(name = "REVIEWS_STAT")
    private String reviewsStat;

    @Column(name = "SATISFIED_CUSTOMERS_STAT")
    private String satisfiedCustomersStat;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

}
