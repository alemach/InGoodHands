package ale.mach.charity.service;

import ale.mach.charity.model.Category;
import ale.mach.charity.repository.CategoryRepository;
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
    public Category findById(int id) {
        Category invalidCategory = new Category();
        invalidCategory.setId(-1);
        invalidCategory.setName("invalid id, category does not exist");
        return categoryRepository.findById(id).orElse(invalidCategory);
    }
}
