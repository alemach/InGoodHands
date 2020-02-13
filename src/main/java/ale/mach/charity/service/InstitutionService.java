package ale.mach.charity.service;

import ale.mach.charity.model.Institution;
import javassist.NotFoundException;

import java.util.List;

public interface InstitutionService {

    List<Institution> findAll();

    Institution findById(int id) throws NotFoundException;
}
