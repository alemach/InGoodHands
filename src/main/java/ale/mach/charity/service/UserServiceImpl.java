package ale.mach.charity.service;

import ale.mach.charity.model.Role;
import ale.mach.charity.model.User;
import ale.mach.charity.model.VerificationToken;
import ale.mach.charity.pojo.PasswordDTO;
import ale.mach.charity.pojo.UserDetailsDTO;
import ale.mach.charity.principal.CustomPrincipal;
import ale.mach.charity.repository.RoleRepository;
import ale.mach.charity.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final EmailService emailService;
	private final VerificationTokenService verificationTokenService;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService, VerificationTokenService verificationTokenService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.verificationTokenService = verificationTokenService;
	}

	@Override
	public void sendPasswordResetLink(String email) throws NotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {

			VerificationToken verificationToken = verificationTokenService.findByUser(user.get());
			verificationToken.setToken(UUID.randomUUID().toString());
			verificationToken.seNewExpires();
			verificationTokenService.create(verificationToken);

			String subject = "Resetowanie hasła do konta na portalu \"Oddam w dobre ręce\"";
			String text = String.format(
					"W celu zmiany hasła do konta proszę kliknąć na poniższy link:%n" +
					"http://localhost:8080/change-password?token=%s", verificationToken.getToken());

			emailService.sendSimpleMessage(user.get().getEmail(), subject, text);
		} else {
			throw new NotFoundException("invalid.email.user.not.found.error.message");
		}
	}

	@Override
	public void updatePassword(PasswordDTO passwordDTO) throws Exception {
		VerificationToken verificationToken = verificationTokenService.findByToken(passwordDTO.getToken());

		if (LocalDateTime.now().isBefore(verificationToken.getExpires())) {
			User user = verificationToken.getUser();
			user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
			userRepository.save(user);

		} else {
			throw new Exception("token.expiration.error.message");
		}
	}

	@Override
	public void toggleAdminRole(int id) throws NotFoundException {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Invalid id. User with id = " + id + " does not exist"));
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		if (user.getRoles().contains(adminRole)) {
			user.getRoles().remove(adminRole);
		} else {
			user.getRoles().add(adminRole);
		}
		userRepository.save(user);
	}

	@Override
	public void updateEnabled(int id, boolean enabled) {
		userRepository.updateUsersEnabled(id, enabled);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> findAll(@AuthenticationPrincipal CustomPrincipal principal) {
		List<User> users = userRepository.findAll();
		users.remove(principal.getMyUser());
		return users;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void create(User user) {

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(UUID.randomUUID().toString());
		verificationToken.setUser(user);

		String subject = "Potwierdzenie rejestracji nowego konta na portalu \"Oddam w dobre ręce\"";
		String text = String.format(
				"Dziękujemy za zarejestrowanie nowego konta na portalu \"Oddam w dobre ręce\".%n" +
				"W celu aktywacji konta proszę kliknąć na poniższy link:%n" +
				"http://localhost:8080/verify-email?token=%s"
				, verificationToken.getToken());

		emailService.sendSimpleMessage(user.getEmail(), subject, text);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(false);
		user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
		userRepository.save(user);

		verificationTokenService.create(verificationToken);
	}

	@Override
	public void activateAccount(String token) throws Exception {

		VerificationToken verificationToken = verificationTokenService.findByToken(token);

		if (LocalDateTime.now().isBefore(verificationToken.getExpires())) {
			User user = verificationToken.getUser();
			user.setEnabled(true);
			userRepository.save(user);

		} else {
			throw new Exception("token.expiration.error.message");
		}
	}

	@Override
	public void update(@AuthenticationPrincipal CustomPrincipal principal, UserDetailsDTO userDetailsDTO) {
		User user = principal.getMyUser();
		user.setFirstName(userDetailsDTO.getFirstName());
		user.setLastName(userDetailsDTO.getLastName());
		user.setPhone(userDetailsDTO.getPhone());
		user.setStreet(userDetailsDTO.getStreet());
		user.setCity(userDetailsDTO.getCity());
		user.setZipCode(userDetailsDTO.getZipCode());

		userRepository.save(user);
	}

	@Override
	public UserDetailsDTO createUserDetails(@AuthenticationPrincipal CustomPrincipal principal) {
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		userDetailsDTO.setFirstName(principal.getMyUser().getFirstName());
		userDetailsDTO.setLastName(principal.getMyUser().getLastName());
		userDetailsDTO.setPhone(principal.getMyUser().getPhone());
		userDetailsDTO.setStreet(principal.getMyUser().getStreet());
		userDetailsDTO.setCity(principal.getMyUser().getCity());
		userDetailsDTO.setZipCode(principal.getMyUser().getZipCode());

		return userDetailsDTO;
	}

	@Override
	public void updateUserCredentials(@AuthenticationPrincipal CustomPrincipal principal, User updatedUser, String oldPassword) throws AuthenticationException {
		if (!passwordEncoder.matches(oldPassword, principal.getMyUser().getPassword())) {
			throw new AuthenticationException("password.validation.error.message");
		}
		User user = principal.getMyUser();
		user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
		user.setEmail(updatedUser.getEmail());
		userRepository.save(user);
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	}

}
