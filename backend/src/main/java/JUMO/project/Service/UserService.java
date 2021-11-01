package JUMO.project.Service;

import JUMO.project.Entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> Signup(String userId, String password);
    Optional<User> login(String userId, String password);
    Optional<User> reset(Long userUid);
}
