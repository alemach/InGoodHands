package ale.mach.charity.controllers;

import ale.mach.charity.model.Category;
import ale.mach.charity.model.Donation;
import ale.mach.charity.model.Institution;
import ale.mach.charity.service.CategoryService;
import ale.mach.charity.service.InstitutionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/logged")
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

    @GetMapping("/donate")
    public String donate(Model model){
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "/logged/form";
    }
}
