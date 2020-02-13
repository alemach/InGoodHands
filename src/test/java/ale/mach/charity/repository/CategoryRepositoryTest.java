package ale.mach.charity.repository;

import ale.mach.charity.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void givenCategoryId_whenFindById_thenReturnCategory() {
        //given
        int categoryId = 1;
        Category category = new Category();
        category.setName("clothing");
        testEntityManager.persist(category);
        //when
        Category testCategory = categoryRepository.findById(categoryId).get();
        //then
        assertEquals(category, testCategory);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenNoCategory_whenFindById_thenThrowNoSuchElement() {
        //given
        int categoryId = 1;
        //when
        Category testCategory = categoryRepository.findById(categoryId).get();
        //then

    }
}