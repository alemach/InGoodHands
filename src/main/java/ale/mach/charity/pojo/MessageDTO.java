package ale.mach.charity.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageDTO {

	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@NotBlank
	private String message;

}
