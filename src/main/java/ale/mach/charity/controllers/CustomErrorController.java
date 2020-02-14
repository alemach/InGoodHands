package ale.mach.charity.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @RequestMapping("")
    public String error(Exception e, Model model, HttpServletRequest request) {
        model.addAttribute("exception", e);
        model.addAttribute("url", request.getRequestURL());
        return "/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
