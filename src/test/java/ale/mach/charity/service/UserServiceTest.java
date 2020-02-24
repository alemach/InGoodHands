package ale.mach.charity.service;

import ale.mach.charity.model.User;
import ale.mach.charity.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	private final String EMAIL = "user@users.com";
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findByEmail() {
		User user = new User();
		user.setFirstName("user");
		user.setEmail(EMAIL);
		user.setPassword("password");
		userRepository.save(user);
		assertTrue(userRepository.findByEmail(EMAIL).isPresent());
	}

	@Test
	public void createUser() {
	}
}