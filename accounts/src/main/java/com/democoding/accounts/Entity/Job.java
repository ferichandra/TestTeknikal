package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "jobs")
public class Job {
    @Id
    @SequenceGenerator(name = "jobs_id_seq", sequenceName = "jobs_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobs_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "start_at")
    private Timestamp startAt;

    @Column(name = "end_at")
    private Timestamp endAt;

}
