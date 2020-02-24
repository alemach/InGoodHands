package ale.mach.charity.service;

import ale.mach.charity.model.DonationStatus;
import ale.mach.charity.repository.DonationStatusRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationStatusServiceImpl implements DonationStatusService {

	private final DonationStatusRepository donationStatusRepository;

	public DonationStatusServiceImpl(DonationStatusRepository donationStatusRepository) {
		this.donationStatusRepository = donationStatusRepository;
	}

	@Override
	public DonationStatus findById(int id) throws NotFoundException {
		return donationStatusRepository.findById(id).orElseThrow(()->new NotFoundException("donationStatus.not.found.error.message"));
	}

	@Override
	public DonationStatus findByStatus(String status) throws NotFoundException {
		return donationStatusRepository.findByStatus(status).orElseThrow(()->new NotFoundException("donationStatus.not.found.error.message"));
	}

	@Override
	public List<DonationStatus> findAll() {
		return donationStatusRepository.findAll();
	}
}
