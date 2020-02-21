package ale.mach.charity.repository;

import ale.mach.charity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE charity_donation.users SET enabled = :enabled WHERE id = :id", nativeQuery = true)
    void updateUsersEnabled(@Param("id") int id, @Param("enabled") boolean enabled);
}
