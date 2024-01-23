package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Mahasiswa {
    @Id
    @SequenceGenerator(name = "mahasiswa_id_seq", sequenceName = "mahasiswa_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mahasiswa_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "nim")
    private String nim;

    @Column(name = "age")
    private Integer age;

    @Column(name = "ipk")
    private Float ipk;

}
