package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Mahasiswa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MahasiswaRepository extends CrudRepository<Mahasiswa,Long> {
    @Query("select a from Mahasiswa a where lower(a.nim) = :nim ")
    Mahasiswa findMahasiswaByNim(@Param("nim")String nim);
}
