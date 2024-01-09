package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
    @Query("select a from Users a where a.username = :username and a.password = :password")
    Users findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Users findByUsername(String username);
}
