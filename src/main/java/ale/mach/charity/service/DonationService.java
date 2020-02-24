package ale.mach.charity.service;

import ale.mach.charity.model.Donation;
import javassist.NotFoundException;

import java.util.List;

public interface DonationService {

	List<Donation> findAllByLoggedUser(String direction, String sortedBy);

	void updateStatus(int donationId, int statusId) throws NotFoundException;

	List<Donation> findAll();

	int getDonationsAmount();

	int getBagsTotalAmount();

	void createDonation(Donation donation) throws NotFoundException;
}
