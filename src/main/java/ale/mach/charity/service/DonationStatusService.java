package ale.mach.charity.service;

import ale.mach.charity.model.DonationStatus;
import javassist.NotFoundException;

import java.util.List;

public interface DonationStatusService {

	DonationStatus findById(int id) throws NotFoundException;

	DonationStatus findByStatus(String status) throws NotFoundException;

	List<DonationStatus> findAll();
}
