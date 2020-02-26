package ale.mach.charity.controllers;

import ale.mach.charity.pojo.MessageDTO;
import ale.mach.charity.pojo.PasswordDTO;
import ale.mach.charity.service.UserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgottenPasswordController {

	private final UserService userService;

	public ForgottenPasswordController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("messageDTO")
	public MessageDTO addMessageDTO(){
		return new MessageDTO();
	}


	@GetMapping("/password-reset")
	public String resetFormAction() {
		return "/reset-form";
	}

	@PostMapping("/password-reset")
	public String resetPassword(@RequestParam String email, RedirectAttributes redirectAttributes) {
		try {
			userService.sendPasswordResetLink(email);
		} catch (NotFoundException e) {
			redirectAttributes.addFlashAttribute("error", e.getLocalizedMessage());
			return "redirect:/password-reset";
		}
		return "redirect:/";
	}

	@GetMapping("/change-password")
	public String changePasswordFormAction(@RequestParam("token") String token, Model model) {
		model.addAttribute("passwordDTO", new PasswordDTO(token));
		return "/change-password";
	}

	@PostMapping("/change-password")
	public String changePassword(@Validated PasswordDTO passwordDTO, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()){
			return "/change-password";
		}
		try {
			userService.updatePassword(passwordDTO);
		} catch (Exception e) {
			result.reject(e.getLocalizedMessage());
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.passwordDTO", result);
			redirectAttributes.addFlashAttribute("passwordDTO", passwordDTO);
			return "/change-password";
		}
		return "redirect:/login";
	}
}
