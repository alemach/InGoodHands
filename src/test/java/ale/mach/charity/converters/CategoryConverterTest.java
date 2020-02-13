package ale.mach.charity.converters;

import ale.mach.charity.service.CategoryService;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryConverterTest {

    private CategoryConverter categoryConverter;
    @Mock
    private CategoryService categoryService;
    private int id = 100;


    @Before
    public void setUp() {
        categoryConverter = new CategoryConverter(categoryService);
        try {
            when(categoryService.findById(id)).thenThrow(new NotFoundException("no category with id"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test(expected = NotFoundException.class)
    public void givenWrongId_whenConvert_thenThrowException() {
        //given
        categoryConverter.convert(id);
    }
}