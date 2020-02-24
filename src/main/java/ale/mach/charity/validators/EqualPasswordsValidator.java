package ale.mach.charity.validators;

import ale.mach.charity.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, User> {
	@Override
	public void initialize(EqualPasswords constraintAnnotation) {

	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {

		return user != null && user.getPassword().equals(user.getRepassword());
	}
}
