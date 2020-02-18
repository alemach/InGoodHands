package ale.mach.charity.service;

import ale.mach.charity.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    void createUser(User user);
}
