package ale.mach.charity.controllers;

import ale.mach.charity.model.Category;
import ale.mach.charity.model.Donation;
import ale.mach.charity.model.Institution;
import ale.mach.charity.model.User;
import ale.mach.charity.pojo.UserDetailsDTO;
import ale.mach.charity.principal.CustomPrincipal;
import ale.mach.charity.service.CategoryService;
import ale.mach.charity.service.DonationService;
import ale.mach.charity.service.InstitutionService;
import ale.mach.charity.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	private final CategoryService categoryService;
	private final InstitutionService institutionService;
	private final DonationService donationService;
	private final UserService userService;

	public UserController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, UserService userService) {
		this.categoryService = categoryService;
		this.institutionService = institutionService;
		this.donationService = donationService;
		this.userService = userService;
	}

	@ModelAttribute("categories")
	public List<Category> categories() {
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
	public String create(@Valid Donation donation, BindingResult result) {
		if (result.hasErrors()) {
			return "/user/form";
		}
		try {
			donationService.createDonation(donation);
		} catch (NotFoundException e) {
			result.reject(e.getLocalizedMessage());
			return "/user/form";
		}
		return "/user/success";
	}

	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal CustomPrincipal principal, Model model) {
		UserDetailsDTO userDetailsDTO = userService.createUserDetails(principal);
		model.addAttribute("userDetailsDTO", userDetailsDTO);
		return "/user/profile";
	}

	@PostMapping("/edit")
	public String update(@AuthenticationPrincipal CustomPrincipal principal, @Validated UserDetailsDTO userDetailsDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/user/profile";
		}
		userService.update(principal, userDetailsDTO);
		return "redirect:/user/donate";
	}

	@GetMapping("/edit-credentials")
	public String editCredentials(@AuthenticationPrincipal CustomPrincipal principal, Model model) {
		User user = new User();
		user.setEmail(principal.getUsername());
		model.addAttribute("user", user);
		return "/user/credentials";
	}

	@PostMapping("/edit-credentials")
	public String updateCredentials(@AuthenticationPrincipal CustomPrincipal principal, @Validated User user, BindingResult result, @RequestParam String oldPassword, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "/user/credentials";
		}
		try {
			userService.updateUserCredentials(principal, user, oldPassword);
		} catch (AuthenticationException e) {
			result.reject(e.getMessage());
			return "/user/credentials";

		}
		return "redirect:/login";
	}

	@GetMapping("/donations")
	public String showDonations(@RequestParam(required = false) String direction, String sortedBy, Model model) {
		model.addAttribute("donations", donationService.findAllByLoggedUser(direction, sortedBy));
		if (direction == null) {
			direction = "asc";
		}
		model.addAttribute("direction", direction);
		return "/user/donation-list-user";
	}
}
