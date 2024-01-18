package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Konsumen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KonsumenRepository extends CrudRepository<Konsumen, Long> {
    @Query("select a from Konsumen a where lower(a.nama) like %:nama% and a.deleteAt is null order by a.nama")
    Page<Konsumen> findallKonsumenPagination(Pageable page, @Param("nama") String nama);

    @Query("select a from Konsumen a where  a.deleteAt is null order by a.nama")
    List<Konsumen> findallKonsumenList();

    @Query("select a from Konsumen a where a.id = :id and a.deleteAt is null ")
    Konsumen findByIdAAndDeleteAtIsNull(@Param("id") Long id);
}
