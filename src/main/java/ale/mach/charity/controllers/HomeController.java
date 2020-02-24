package ale.mach.charity.controllers;

import ale.mach.charity.model.User;
import ale.mach.charity.service.DonationService;
import ale.mach.charity.service.InstitutionService;
import ale.mach.charity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

	private final InstitutionService institutionService;
	private final DonationService donationService;
	private final UserService userService;

	public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService) {
		this.institutionService = institutionService;
		this.donationService = donationService;
		this.userService = userService;
	}

	@RequestMapping("")
	public String homeAction(Model model) {
		model.addAttribute("institutions", institutionService.findAll());
		model.addAttribute("donationsAmount", donationService.getDonationsAmount());
		model.addAttribute("bagsAmount", donationService.getBagsTotalAmount());
		return "/index";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "/register";
	}

	@PostMapping("/register")
	public String register(@Validated User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/register";
		}
		userService.create(user);
		return "redirect:/login";
	}
}
