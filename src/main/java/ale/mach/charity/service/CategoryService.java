package ale.mach.charity.service;

import ale.mach.charity.model.Category;
import javassist.NotFoundException;

import java.util.List;

public interface CategoryService {
	List<Category> findAll();

	Category findById(int id) throws NotFoundException;
}
