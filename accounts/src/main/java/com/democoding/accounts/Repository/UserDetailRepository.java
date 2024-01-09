package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
    UserDetail findUserDetailByUserId(Integer id);
}
