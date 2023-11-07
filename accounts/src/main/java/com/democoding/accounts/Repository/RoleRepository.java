package com.democoding.accounts.Repository;

import com.democoding.accounts.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findRoleById(Long id);
}
