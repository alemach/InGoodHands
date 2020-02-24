package ale.mach.charity.repository;

import ale.mach.charity.model.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationStatusRepository extends JpaRepository<DonationStatus, Integer> {

	Optional<DonationStatus> findByStatus(String status);
}
