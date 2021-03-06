package JUMO.project.Repository;

import JUMO.project.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    //Constructor
    public UserRepositoryImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        List<User> user=entityManager
                .createQuery("SELECT u FROM User u WHERE u.id=:id",User.class)
                .setParameter("id",id)
                .getResultList();

        return user.stream().findAny();
    }

    @Override
    public Optional<User> findByUid(Long uid) {
        User user = entityManager.find(User.class, uid);
        return Optional.of(user);
    }

}
