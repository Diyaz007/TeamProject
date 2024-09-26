package com.example.teamproject.entity;

import com.example.teamproject.enums.MovingSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Users usersID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_ID")
    private Company companyID;

    @Column(name = "ZIP_POINT_A")
    private String zipPointA;

    @Column(name = "ZIP_POINT_B")
    private String zipPointB;

    @Column(name = "MOVING_DATE")
    private Date movingDate;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "MOVING_SIZE")
    private MovingSize movingSize;

    @Column(name = "CREATED_DATE")
    private Date createdDate;


}
