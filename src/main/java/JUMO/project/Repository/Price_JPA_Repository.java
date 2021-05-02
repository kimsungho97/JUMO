package JUMO.project.Repository;

import JUMO.project.Entity.Price;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Primary
@Repository
public class Price_JPA_Repository implements Price_Repository{
    private final EntityManager entityManager;

    public Price_JPA_Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Price> findAll(){
        return entityManager.createQuery("select p from Price p where p.id.name='DSR'",Price.class).getResultList();
    }

    @Override
    public List<Price> findByname(String name){
        return entityManager.createQuery("select p from Price p where p.id.name like '%"+name+"%'",Price.class)
                .getResultList();
    }

    @Override
    public List<Price> findBycode(String code){
        return entityManager.createQuery("select p from Price p where p.code like '%"+code+"%'",Price.class)
                .getResultList();
    }
}
