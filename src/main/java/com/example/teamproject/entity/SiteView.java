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

    @Column(name = "WELCOME_TEXT")
    private String welcomeText;

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
