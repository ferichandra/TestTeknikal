package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Matakuliah {
    @Id
    @SequenceGenerator(name = "matakuliah_id_seq", sequenceName = "matakuliah_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matakuliah_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama_matakuliah")
    private String nama;

    @Column(name = "sks")
    private Integer sks;


}
