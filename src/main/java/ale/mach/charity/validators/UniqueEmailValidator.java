package ale.mach.charity.validators;

import ale.mach.charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void initialize(UniqueEmail constraintAnnotation) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		String oldEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		if (email.equals("")) {
			return true;
		} else if (oldEmail.equals(email) && !oldEmail.equals("anonymousUser")) {
			return true;
		} else {
			return !userService.findByEmail(email).isPresent();
		}
	}
}
