package com.democoding.accounts.Service;

import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NativeQuery {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> listEmployee() {
        try {
            Query query;
            query = this.em.createNativeQuery(
                    "select CONCAT(emp.name+' - '+role.role_name),emp.age,emp.address,emp.email " +
                            "from public.employee emp join public.role role on emp.role_id = role.id where emp.deleted_at is null " +
                            "order by emp.id;"
            );
            List<Object[]> resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            throw new ResourceNotAcceptableException("Error");
        } finally {
            if (this.em != null) {
                this.em.close();
            }
        }
    }
}
