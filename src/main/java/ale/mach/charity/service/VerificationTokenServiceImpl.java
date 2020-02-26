package ale.mach.charity.service;

import ale.mach.charity.model.User;
import ale.mach.charity.model.VerificationToken;
import ale.mach.charity.repository.VerificationTokenRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	private final VerificationTokenRepository verificationTokenRepository;

	public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
		this.verificationTokenRepository = verificationTokenRepository;
	}

	@Override
	public void create(VerificationToken verificationToken) {
		verificationTokenRepository.save(verificationToken);
	}

	@Override
	public VerificationToken findByToken(String token) throws NotFoundException {
		return verificationTokenRepository.findByToken(token).orElseThrow(() -> new NotFoundException("token.validation.error.message"));
	}

	@Override
	public VerificationToken findByUser(User user) throws NotFoundException {
		return verificationTokenRepository.findByUser(user).orElseThrow(() -> new NotFoundException("token.validation.error.message"));
	}
}
