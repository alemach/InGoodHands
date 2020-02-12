package ale.mach.charity.controllers;

import ale.mach.charity.service.DonationService;
import ale.mach.charity.service.InstitutionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("donationsAmount", donationService.getDonationsAmount());
        model.addAttribute("bagsAmount", donationService.getBagsTotalAmount());
        return "/index";
    }
}
