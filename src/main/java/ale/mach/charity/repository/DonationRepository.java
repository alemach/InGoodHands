package ale.mach.charity.repository;

import ale.mach.charity.model.Donation;
import ale.mach.charity.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

	List<Donation> findAllByUser(User user, Sort sort);

	int countDonationsBy();

	@Query(value = "SELECT SUM(quantity) FROM donations;", nativeQuery = true)
	Optional<Integer> sumOfAllQuantity();
}
