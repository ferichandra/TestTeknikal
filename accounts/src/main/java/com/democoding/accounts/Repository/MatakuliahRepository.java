package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Matakuliah;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatakuliahRepository extends CrudRepository<Matakuliah,Long> {
    @Query("select a from Matakuliah a where a.id = :id")
    Matakuliah findMatakuliahById(@Param("id")Long idmahasiswa);
}
