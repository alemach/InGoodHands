package ale.mach.charity.controllers;

import ale.mach.charity.model.Category;
import ale.mach.charity.model.Donation;
import ale.mach.charity.model.Institution;
import ale.mach.charity.service.CategoryService;
import ale.mach.charity.service.DonationService;
import ale.mach.charity.service.InstitutionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public UserController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }

    @GetMapping("/donate")
    public String donate(Model model) {
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "/user/form";
    }

    @PostMapping("/donate")
    public String save(@ModelAttribute Donation donation) {
        donationService.createDonation(donation);
        return "/user/success";
    }
}
