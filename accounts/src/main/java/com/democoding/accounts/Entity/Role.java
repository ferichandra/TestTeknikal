package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table( name = "role")
public class Role {
    @Id
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "createdat")
    private Date createdAt;

    @Column(name = "updateat")
    private Date updateAt;

    @Column(name = "deleteat")
    private Date deleteAt;
}
