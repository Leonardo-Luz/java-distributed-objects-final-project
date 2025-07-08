package ifrs.edu.br.snakegame.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ifrs.edu.br.snakegame.services.ScoreService;
import ifrs.edu.br.snakegame.services.UserService;

import org.springframework.ui.Model;

@Controller
public class FrontController {

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home(Model model) {

		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			model.addAttribute("userCount", userService.count());
			model.addAttribute("scoreCount", scoreService.count());
		}
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
