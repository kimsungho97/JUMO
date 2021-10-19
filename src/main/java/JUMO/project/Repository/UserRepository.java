package JUMO.project.Repository;

import JUMO.project.Entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByUid(Long uid);
}
