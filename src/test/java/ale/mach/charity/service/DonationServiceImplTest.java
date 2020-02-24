package ale.mach.charity.service;

import ale.mach.charity.repository.DonationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DonationServiceImplTest {

	private final int NUMBER_OF_DONATIONS = 5;
	private final int NUMBER_OF_BAGS_PER_DONATION = 2;
	private DonationService donationService;
	@Mock
	private DonationRepository donationRepository;

	@Before
	public void setUp() throws Exception {
		donationService = new DonationServiceImpl(donationRepository);
	}

	@Test
	public void whenGetDonationsAmount_thenReturnAllDonationsCount() {
		//given
		when(donationRepository.countDonationsBy()).thenReturn(NUMBER_OF_DONATIONS);
		//when
		int result = donationService.getDonationsAmount();
		//then
		assertEquals(NUMBER_OF_DONATIONS, result);
	}

	@Test
	public void WhenGetBagsTotalAmount_thenReturnSumOfQuantitiesOfAllDonations() {
		//given
		when(donationRepository.sumOfAllQuantity()).thenReturn(Optional.of(NUMBER_OF_DONATIONS * NUMBER_OF_BAGS_PER_DONATION));
		//when
		int result = donationService.getBagsTotalAmount();
		//then
		assertEquals(NUMBER_OF_DONATIONS * NUMBER_OF_BAGS_PER_DONATION, result);
	}
}