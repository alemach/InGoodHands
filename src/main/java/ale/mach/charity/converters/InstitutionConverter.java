package ale.mach.charity.converters;

import ale.mach.charity.model.Institution;
import ale.mach.charity.service.InstitutionService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InstitutionConverter implements Converter<Integer, Institution> {

	private final InstitutionService institutionService;

	public InstitutionConverter(InstitutionService institutionService) {
		this.institutionService = institutionService;
	}

	@SneakyThrows(NotFoundException.class)
	@Override
	public Institution convert(Integer id) {
		return institutionService.findById(id);
	}
}
