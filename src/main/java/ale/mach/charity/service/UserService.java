package ale.mach.charity.service;

import ale.mach.charity.model.User;
import ale.mach.charity.pojo.UserDetailsDTO;
import ale.mach.charity.principal.CustomPrincipal;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

public interface UserService {

	void toggleAdminRole(int id) throws NotFoundException;

	void updateEnabled(int id, boolean enabled);

	void delete(int id);

	List<User> findAll(@AuthenticationPrincipal CustomPrincipal principal);

	Optional<User> findByEmail(String email);

	void create(User user);

	void update(@AuthenticationPrincipal CustomPrincipal principal, UserDetailsDTO userDetailsDTO);

	UserDetailsDTO createUserDetails(@AuthenticationPrincipal CustomPrincipal principal);

	void updateUserCredentials(@AuthenticationPrincipal CustomPrincipal principal, User user, String oldPassword) throws AuthenticationException;
}
