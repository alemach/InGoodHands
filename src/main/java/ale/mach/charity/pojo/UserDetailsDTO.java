package ale.mach.charity.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class UserDetailsDTO {
	@NotBlank
	@Size(min = 2)
	private String firstName;
	@NotBlank
	@Size(min = 2)
	private String lastName;
	@NotNull
	@Positive
	@Range(min = 100000000, max = 999999999)
	private Integer phone;
	@NotBlank
	@Size(min = 2)
	private String street;
	@NotBlank
	@Size(min = 2)
	private String city;
	@NotBlank
	@Pattern(regexp = "^\\d{2}-\\d{3}$")
	private String zipCode;
}
