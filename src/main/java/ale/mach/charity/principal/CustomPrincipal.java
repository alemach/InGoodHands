package ale.mach.charity.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomPrincipal extends User {

	private ale.mach.charity.model.User myUser;

	public CustomPrincipal(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, ale.mach.charity.model.User myUser) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.myUser = myUser;
	}

	public ale.mach.charity.model.User getMyUser() {
		return myUser;
	}

	public String getMyUserName() {
		return myUser.getFirstName();
	}
}
