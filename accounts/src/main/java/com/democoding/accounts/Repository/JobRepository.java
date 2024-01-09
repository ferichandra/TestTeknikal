package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job,Long> {
    @Query("select a from Job a where a.userId = :userid")
    List<Job> findJobByUserId(@Param("userid")Integer userid);

}
