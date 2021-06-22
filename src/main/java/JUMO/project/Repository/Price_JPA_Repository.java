package JUMO.project.Repository;

import JUMO.project.Entity.Price;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return entityManager.createQuery("select p from Price p where p.id.name = '"+name+"'",Price.class)
                .getResultList();
    }

    @Override
    public List<Price> findBycode(String code){
        return entityManager.createQuery("select p from Price p where p.code like '%"+code+"%'",Price.class)
                .getResultList();
    }

    @Override
    public HashMap<String,String> allStock_name(String name){
        List<Object> prices=entityManager.createQuery("select p.id.name, p.code from Price p").getResultList();
        HashMap<String,String> result=new HashMap<>();
        for(Object object:prices){
            Object[] results=(Object[]) object;
            if(((String)results[0]).contains(name)) {
                result.put((String) results[0], (String) results[1]);
            }
        }
        return result;
    }

    @Override
    public HashMap<String,String> allStock_code(String code){
        List<Object> prices=entityManager.createQuery("select p.id.name, p.code from Price p").getResultList();
        HashMap<String,String> result=new HashMap<>();
        for(Object object:prices){
            Object[] results=(Object[]) object;
            if(((String)results[1]).contains(code)) {
                result.put((String) results[0], (String) results[1]);
            }
        }
        return result;
    }
}
