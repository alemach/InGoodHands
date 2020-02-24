package ale.mach.charity.service;

import ale.mach.charity.model.Category;
import ale.mach.charity.repository.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(int id) throws NotFoundException {
		return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Invalid id. Category with id = " + id + "does not exist"));
	}
}
