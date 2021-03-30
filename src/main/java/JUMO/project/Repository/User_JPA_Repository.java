package JUMO.project.Repository;

import JUMO.project.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class User_JPA_Repository implements User_Repository{

    private final EntityManager entityManager;

    //Constructor
    public User_JPA_Repository(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findbyId(String id) {
        List<User> user=entityManager
                .createQuery("SELECT u FROM user u WHERE u.id=:id")
                .setParameter("id",id)
                .getResultList();

        return user.stream().findAny();
    }

}
