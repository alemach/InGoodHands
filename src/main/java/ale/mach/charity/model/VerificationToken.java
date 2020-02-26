package ale.mach.charity.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String token;
	@OneToOne
	@JoinColumn(nullable = false)
	private User user;
	private LocalDateTime expires = LocalDateTime.now().plusHours(24);

	public void seNewExpires(){
		this.expires = LocalDateTime.now().plusHours(24);
	}
}
