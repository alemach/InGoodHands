package ale.mach.charity.controllers;

import ale.mach.charity.pojo.MessageDTO;
import ale.mach.charity.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class FooterController {

	private final EmailService emailService;

	public FooterController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/send-message")
	public String sendMessage(@Validated MessageDTO messageDTO, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String url = "";
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageDTO", result);
			redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
			return "redirect:" + url + "#contact";
		}
		try {
			url = new URI(request.getHeader("referer")).getPath();
		} catch (URISyntaxException e) {
			result.reject(e.getLocalizedMessage());
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageDTO", result);
			redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
			return "redirect:" + url + "#contact";
		}
		emailService.sendSimpleTemplateMessage(messageDTO);
		redirectAttributes.addFlashAttribute("message", "Wiadomość wysłana");
		return "redirect:" + url + "#contact";
	}
}
