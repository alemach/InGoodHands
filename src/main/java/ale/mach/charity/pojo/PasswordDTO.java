package ale.mach.charity.pojo;

import ale.mach.charity.validators.EqualPasswords;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualPasswords
@Data
public class PasswordDTO {

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
	@NotBlank
	private String password;
	@NotBlank
	private String repassword;
	private String token;

	public PasswordDTO(String token) {
		this.token = token;
	}
}
