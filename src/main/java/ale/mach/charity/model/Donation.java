package ale.mach.charity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name = "donations")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Min(1)
	private int quantity;
	@ManyToMany
	@NotNull
	private List<Category> categories;
	@ManyToOne
	@NotNull
	private Institution institution;
	@NotBlank
	private String street;
	@NotBlank
	private String city;
	@NotBlank
	@Pattern(regexp = "^\\d{2}-\\d{3}$")
	private String zipCode;
	@NotNull
	@Future
	private LocalDate pickUpDate;
	@NotNull
	private LocalTime pickUpTime;
	private String pickUpComment;
	@ManyToOne
	private DonationStatus status;
	@ManyToOne
	private User user;
	private LocalDateTime created;
	private LocalDateTime modified;

}
