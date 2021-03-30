package JUMO.project.Repository;

import JUMO.project.Entity.User;

import java.util.Optional;

public interface User_Repository {
    User save(User user);
    Optional<User> findbyId(String id);
}
