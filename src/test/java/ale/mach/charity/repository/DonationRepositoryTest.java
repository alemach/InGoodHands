package ale.mach.charity.repository;

import ale.mach.charity.model.Donation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DonationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DonationRepository donationRepository;

    @Test
    public void whenCountDonations_thenReturnDonationsTotalAmount() {
        //given
        int donationsAmount = 5;
        for (int i = 0; i < donationsAmount; i++) {
            testEntityManager.persist(new Donation());
        }

        //when
        int result = donationRepository.countDonationsBy();

        //then
        assertEquals(result, donationsAmount);
    }

    @Test
    public void whenQuerySumDonationsQuantities_thenReturnSumOfAllDonationsQuantities() {
        //given
        int donationsAmount = 5;
        int sum = 0;
        for (int i = 1; i < donationsAmount + 1; i++) {
            Donation donation = new Donation();
            donation.setQuantity(i);
            testEntityManager.persist(donation);
            sum += i;
        }

        //when
        int result = donationRepository.sumOfAllQuantity();

        //then
        assertEquals(result, sum);
    }
}
