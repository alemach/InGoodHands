package ale.mach.charity.service;

import ale.mach.charity.model.Institution;
import javassist.NotFoundException;

import java.util.List;

public interface InstitutionService {

    void createUpdate(Institution institution);

    void delete(int id);

    List<Institution> findAll();

    Institution findById(int id) throws NotFoundException;
}
