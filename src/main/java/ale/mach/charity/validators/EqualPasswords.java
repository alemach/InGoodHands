package ale.mach.charity.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EqualPasswordsValidator.class, EqualPasswordsDTOValidator.class})
public @interface EqualPasswords {
	String message() default "{equalPasswords.error.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
