package ale.mach.charity.validators;

import ale.mach.charity.pojo.PasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsDTOValidator implements ConstraintValidator<EqualPasswords, PasswordDTO> {
	@Override
	public void initialize(EqualPasswords constraintAnnotation) {

	}

	@Override
	public boolean isValid(PasswordDTO passwordDTO, ConstraintValidatorContext context) {

		return passwordDTO != null && passwordDTO.getPassword().equals(passwordDTO.getRepassword());
	}
}
