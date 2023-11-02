package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.validation.Schema;
import java.util.Date;

@Entity
@Data
@Table( name = "employee")
public class Employee {
    @Id
    @SequenceGenerator(name = "employe_seq", sequenceName = "employe_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_seq")
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
}
