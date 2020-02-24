package ale.mach.charity.service;

import ale.mach.charity.model.User;
import ale.mach.charity.principal.CustomPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Autowired
	public void setUserRepository(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new CustomPrincipal(email, user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities, user);
	}
}
