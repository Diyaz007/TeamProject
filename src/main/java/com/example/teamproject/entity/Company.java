package com.example.teamproject.entity;

import com.example.teamproject.enums.Roles;
import com.example.teamproject.enums.States;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Users usersID;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "COMAPANY_NAME",unique = true)
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    @Enumerated(EnumType.STRING)
    private States address;

    @Lob
    @Column(name = "INFO")
    private String info;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<Review> reviews;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public void addImageToCompany(Image image) {
        image.setCompany(this);
        images.add(image);
    }


}


