package ale.mach.charity.converters;

import ale.mach.charity.model.Category;
import ale.mach.charity.service.CategoryService;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Integer, Category> {

	private final CategoryService categoryService;

	public CategoryConverter(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@SneakyThrows
	@Override
	public Category convert(Integer id) {
		return categoryService.findById(id);
	}
}
