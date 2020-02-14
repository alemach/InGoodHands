package ale.mach.charity.service;

import ale.mach.charity.repository.DonationRepository;
import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public int getDonationsAmount() {
        return donationRepository.countDonationsBy();
    }

    @Override
    public int getBagsTotalAmount() {
        return donationRepository.sumOfAllQuantity().orElse(0);
    }
}
