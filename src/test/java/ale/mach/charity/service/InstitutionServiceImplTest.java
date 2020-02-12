package ale.mach.charity.service;

import ale.mach.charity.model.Institution;
import ale.mach.charity.repository.InstitutionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class InstitutionServiceImplTest {

    private InstitutionRepository institutionRepository;
    private InstitutionService institutionService;

    @Before
    public void setUp() throws Exception {
        institutionRepository = mock(InstitutionRepository.class);
        institutionService = new InstitutionServiceImpl(institutionRepository);
    }

    @Test
    public void whenGetAll_thenReturnListOfInstitutions() {
        //given
        int numberOfInstitutions = 5;
        when(institutionRepository.findAll()).thenReturn(Arrays.asList(new Institution[numberOfInstitutions]));

        //when
        List<Institution> result = institutionService.findAll();

        //then
        assertNotNull(result);
        assertEquals(numberOfInstitutions, result.size());
    }
}