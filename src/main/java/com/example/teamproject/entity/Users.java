package com.example.teamproject.entity;

import com.example.teamproject.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Integer id;

    private Boolean active;

    @Column(name = "USERNAME",unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    

}

