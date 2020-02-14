package ale.mach.charity.repository;

import ale.mach.charity.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    int countDonationsBy();

    @Query(value = "SELECT SUM(quantity) FROM donations;", nativeQuery = true)
    Optional<Integer> sumOfAllQuantity();
}
