package ale.mach.charity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name = "donations")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;
	@ManyToMany
	private List<Category> categories;
	@ManyToOne
	private Institution institution;
	private String street;
	private String city;
	private String zipCode;
	private LocalDate pickUpDate;
	private LocalTime pickUpTime;
	private String pickUpComment;
}
