package com.democoding.accounts.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Nilai {
    @Id
    @SequenceGenerator(name = "nilai_id_seq", sequenceName = "nilai_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nilai_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_matakuliah")
    private Integer idMatakuliah;

    @Column(name = "nilai")
    private String nilai;

    @Column(name = "id_mahasiswa")
    private Integer idMahasiswa;

//    @OneToMany(mappedBy = "id", targetEntity = Matakuliah.class, cascade = CascadeType.ALL)
//    private List<Matakuliah> matakuliahList;
//
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Mahasiswa.class, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
//    @JoinColumn(name = "id_mahasiswa", referencedColumnName = "id", nullable = false)
//    private Mahasiswa mahasiswa;

}
