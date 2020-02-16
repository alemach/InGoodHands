package ale.mach.charity.service;

import ale.mach.charity.model.Donation;

public interface DonationService {

    int getDonationsAmount();

    int getBagsTotalAmount();

    void createDonation(Donation donation);
}
