package ale.mach.charity.controllers;

import ale.mach.charity.model.Institution;
import ale.mach.charity.service.DonationService;
import ale.mach.charity.service.InstitutionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {
    private static final String HOME_VIEW = "/index";
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private DonationService donationService;
    @MockBean
    private InstitutionService institutionService;
    private final int DONATIONS_AMOUNT = 5;
    private final int BAGS_AMOUNT = 13;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        List<Institution> institutionList = Arrays.asList(new Institution[3]);
        when(this.institutionService.findAll()).thenReturn(institutionList);

        when(this.donationService.getDonationsAmount()).thenReturn(DONATIONS_AMOUNT);

        when(this.donationService.getBagsTotalAmount()).thenReturn(BAGS_AMOUNT);
    }

    @Test
    public void homeAction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(model().attributeExists("institutions"))
                .andExpect(model().attribute("institutions", hasSize(3)))
                .andExpect(model().attributeExists("donationsAmount"))
                .andExpect(model().attribute("donationsAmount", DONATIONS_AMOUNT))
                .andExpect(model().attributeExists("bagsAmount"))
                .andExpect(model().attribute("bagsAmount", BAGS_AMOUNT))
                .andExpect(MockMvcResultMatchers.view().name(HOME_VIEW));
    }
}