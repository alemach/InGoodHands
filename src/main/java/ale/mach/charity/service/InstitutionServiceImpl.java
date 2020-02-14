package ale.mach.charity.service;

import ale.mach.charity.model.Institution;
import ale.mach.charity.repository.InstitutionRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public Institution findById(int id) {
        return institutionRepository.findById(id).orElseThrow(() -> new NotFoundException("Invalid id. Institution with id = " + id + "does not exist"));
    }
}
