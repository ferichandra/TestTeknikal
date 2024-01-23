package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Nilai;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NilaiRepository extends CrudRepository<Nilai,Long> {
    @Query("select a from Nilai a where a.idMahasiswa = :idmahasiswa")
    List<Nilai> findNilaiByIdMahasiswa(@Param("idmahasiswa")Integer idmahasiswa);
}
