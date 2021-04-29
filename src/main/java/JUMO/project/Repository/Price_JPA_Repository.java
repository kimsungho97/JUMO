package JUMO.project.Repository;

import JUMO.project.Entity.Price;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class Price_JPA_Repository {
    private final EntityManager entityManager;

    public Price_JPA_Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Price> findAll(){
        return entityManager.createQuery("select p from price p",Price.class).getResultList();
    }

    public List<Price> findByname(String name){
        return entityManager.createQuery("select p from price p where p.name like %:name%",Price.class)
                .setParameter("name",name)
                .getResultList();
    }

    public List<Price> findBycode(String code){
        return entityManager.createQuery("select p from price p where p.code like %:code%",Price.class)
                .setParameter("code",code)
                .getResultList();
    }
}
