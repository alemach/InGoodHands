package ale.mach.charity.controllers;

import ale.mach.charity.model.DonationStatus;
import ale.mach.charity.model.Institution;
import ale.mach.charity.pojo.MessageDTO;
import ale.mach.charity.principal.CustomPrincipal;
import ale.mach.charity.service.DonationService;
import ale.mach.charity.service.DonationStatusService;
import ale.mach.charity.service.InstitutionService;
import ale.mach.charity.service.RoleService;
import ale.mach.charity.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserService userService;
	private final RoleService roleService;
	private final InstitutionService institutionService;
	private final DonationService donationService;
	private final DonationStatusService donationStatusService;

	public AdminController(UserService userService, RoleService roleService, InstitutionService institutionService, DonationService donationService, DonationStatusService donationStatusService) {
		this.userService = userService;
		this.roleService = roleService;
		this.institutionService = institutionService;
		this.donationService = donationService;
		this.donationStatusService = donationStatusService;
	}
	@ModelAttribute("messageDTO")
	public MessageDTO addMessageDTO(){
		return new MessageDTO();
	}

	@ModelAttribute("donationStatuses")
	public List<DonationStatus> donationStatuses() {
		return donationStatusService.findAll();
	}

	@GetMapping("/dashboard")
	public String showDashboard() {
		return "/admin/dashboard";
	}

	@GetMapping("/users")
	public String showUsers(@AuthenticationPrincipal CustomPrincipal principal, Model model) {
		model.addAttribute("users", userService.findAll(principal));
		model.addAttribute("adminRole", roleService.findByName("ROLE_ADMIN"));
		return "/admin/user-list";
	}

	@PostMapping("/delete-user")
	public String deleteUser(@RequestParam int id) {
		userService.delete(id);
		return "redirect:/admin/users";
	}

	@PostMapping("/toggle-users-enable")
	public String toggleUsersEnabled(@RequestParam int id, boolean enabled) {
		userService.updateEnabled(id, enabled);
		return "redirect:/admin/users";
	}

	@PostMapping("/toggle-role")
	public RedirectView toggleAdminRole(@RequestParam int id, RedirectAttributes redirectAttributes) {
		try {
			userService.toggleAdminRole(id);
		} catch (NotFoundException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return new RedirectView("/admin/users", true);

	}

	@GetMapping("/institutions")
	public String showInstitutions(Model model) {
		model.addAttribute("institutions", institutionService.findAll());
		model.addAttribute("institution", new Institution());
		return "/admin/institution-list";
	}

	@PostMapping("/delete-institution")
	public String deleteInstitution(@RequestParam int id) {
		institutionService.delete(id);
		return "redirect:/admin/institutions";
	}

	@PostMapping("/create-update-institution")
	public String createUpdateInstitution(@ModelAttribute Institution institution) {
		institutionService.createUpdate(institution);
		return "redirect:/admin/institutions";
	}

	@GetMapping("/donations")
	public String showDonations(Model model) {
		model.addAttribute("donations", donationService.findAll());
		return "/admin/donation-list-admin";
	}

	@PostMapping("/donations")
	public String changeStatus(@RequestParam int donationId, @RequestParam int statusId, Model model) {
		try {
			donationService.updateStatus(donationId, statusId);
		} catch (NotFoundException e) {
			model.addAttribute("error", e.getLocalizedMessage());
		}
		return "redirect:/admin/donations";
	}
}
