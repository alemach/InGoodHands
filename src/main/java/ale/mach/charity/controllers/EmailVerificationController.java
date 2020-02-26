package ale.mach.charity.controllers;

import ale.mach.charity.pojo.MessageDTO;
import ale.mach.charity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmailVerificationController {

	private final UserService userService;

	public EmailVerificationController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("messageDTO")
	public MessageDTO addMessageDTO(){
		return new MessageDTO();
	}

	@GetMapping("/verify-email")
	public String verifyEmail(@RequestParam("token") String token, RedirectAttributes redirectAttributes){
		try {
			userService.activateAccount(token);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getLocalizedMessage());
			return "/activation-error";
		}
		redirectAttributes.addFlashAttribute("success", "Konto pomyślnie aktywowane.");
		return "redirect:/login";
	}
}
