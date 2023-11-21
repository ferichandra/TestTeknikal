package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.validation.Schema;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "createdat")
    private Date createdAt;

    @Column(name = "updateat")
    private Date updateAt;

    @Column(name = "deleteat")
    private Date deleteAt;

    @Column(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Integer roledId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
}
