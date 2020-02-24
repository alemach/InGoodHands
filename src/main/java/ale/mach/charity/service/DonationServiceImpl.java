package ale.mach.charity.service;

import ale.mach.charity.model.Donation;
import ale.mach.charity.principal.CustomPrincipal;
import ale.mach.charity.repository.DonationRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
	private final DonationRepository donationRepository;
	private final DonationStatusService donationStatusService;

	public DonationServiceImpl(DonationRepository donationRepository, DonationStatusService donationStatusService) {
		this.donationRepository = donationRepository;
		this.donationStatusService = donationStatusService;
	}

	@Override
	public List<Donation> findAllByLoggedUser(String dir, String sortedBy) {
		if (dir == null || sortedBy == null) {
			dir = "asc";
			sortedBy = "status.status";
		}
		Sort.Direction direction = null;
		if (dir.equals("asc")) {
			direction = Sort.Direction.ASC;
		} else if (dir.equals("desc")) {
			direction = Sort.Direction.DESC;
		}
		return donationRepository.findAllByUser(((CustomPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMyUser(), Sort.by(direction, sortedBy));
	}

	@Override
	public void updateStatus(int donationId, int statusId) throws NotFoundException {
		Donation donation = donationRepository.findById(donationId).orElseThrow(() -> new NotFoundException("donation.not.found.error.message"));
		donation.setStatus(donationStatusService.findById(statusId));
		donation.setModified(LocalDateTime.now());
		donationRepository.save(donation);
	}

	@Override
	public List<Donation> findAll() {
		return donationRepository.findAll();
	}

	@Override
	public int getDonationsAmount() {
		return donationRepository.countDonationsBy();
	}

	@Override
	public int getBagsTotalAmount() {
		return donationRepository.sumOfAllQuantity().orElse(0);
	}

	@Override
	public void createDonation(Donation donation) throws NotFoundException {
		String donationStatus = "Nieodebrane";
		donation.setUser(((CustomPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMyUser());
		donation.setStatus(donationStatusService.findByStatus(donationStatus));
		donation.setCreated(LocalDateTime.now());
		donationRepository.save(donation);
	}
}
