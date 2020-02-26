package ale.mach.charity.service;

import ale.mach.charity.model.User;
import ale.mach.charity.model.VerificationToken;
import javassist.NotFoundException;

public interface VerificationTokenService {

	void create(VerificationToken verificationToken);

	VerificationToken findByToken(String token) throws NotFoundException;

	VerificationToken findByUser(User user) throws NotFoundException;
}
