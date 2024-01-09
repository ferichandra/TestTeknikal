package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserDetail.class, cascade = {CascadeType.REMOVE,CascadeType.MERGE})
//    @JoinColumn(name = "id")
//    private UserDetail userDetail;
//
//    @OneToMany(mappedBy = "id", targetEntity = Job.class, cascade = CascadeType.ALL)
//    private List<Job> jobList;
}
