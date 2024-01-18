package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "konsumen")
public class Konsumen {
    @Id
    @SequenceGenerator(name = "konsumen_id_seq", sequenceName = "konsumen_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "konsumen_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "kota")
    private String kota;

    @Column(name = "provinsi")
    private String provinsi;

    @Column(name = "tgl_registrasi")
    private Date tglRegistrasi;

    @Column(name = "status")
    private String status;

    @Column(name ="delete_at")
    private Date deleteAt;

}
