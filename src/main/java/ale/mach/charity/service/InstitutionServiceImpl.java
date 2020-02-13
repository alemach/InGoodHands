package ale.mach.charity.service;

import ale.mach.charity.model.Institution;
import ale.mach.charity.repository.InstitutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution findById(int id) {
        Institution invalidInstitution = new Institution();
        invalidInstitution.setId(-1);
        invalidInstitution.setName("invalid id, institution does not exist");
        return institutionRepository.findById(id).orElse(invalidInstitution);
    }
}
