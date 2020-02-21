package ale.mach.charity.service;

import ale.mach.charity.model.User;
import ale.mach.charity.principal.CustomPrincipal;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void toggleAdminRole(int id) throws NotFoundException;

    void updateEnabled(int id, boolean enabled);

    void delete(int id);

    List<User> findAll(@AuthenticationPrincipal CustomPrincipal principal);

    Optional<User> findByEmail(String email);

    void create(User user);
}
