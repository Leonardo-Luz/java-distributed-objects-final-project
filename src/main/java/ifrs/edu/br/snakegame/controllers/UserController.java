package ifrs.edu.br.snakegame.controllers;

import ifrs.edu.br.snakegame.dtos.PasswordDTO;
import ifrs.edu.br.snakegame.dtos.UserDTO;
import ifrs.edu.br.snakegame.dtos.UserUpdateDTO;
import ifrs.edu.br.snakegame.entities.User;
import ifrs.edu.br.snakegame.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "register";
	}

	@PostMapping("/register")
	public String processRegister(
			@Valid @ModelAttribute("userDTO") UserDTO userDTO,
			BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}

		if (userService.existsByUsername(userDTO.getUsername())) {
			result.rejectValue("username", "error.userDTO", "Username already exists");
			return "register";
		}

		userService.register(userDTO);
		return "redirect:/login";
	}

	@GetMapping("/profile")
	public String showProfile(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/profile/edit")
	public String showEditProfileForm(@AuthenticationPrincipal User user, Model model) {
		UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
		userUpdateDTO.setUsername(user.getUsername());
		userUpdateDTO.setEmail(user.getEmail());

		model.addAttribute("userUpdateDTO", userUpdateDTO);
		return "edit-profile";
	}

	@PostMapping("/profile/edit")
	public String processEditProfile(
			@Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO,
			BindingResult result,
			@AuthenticationPrincipal User currentUser) {

		if (result.hasErrors()) {
			System.out.println(result);
			return "edit-profile";
		}

		if (!currentUser.getUsername().equals(userUpdateDTO.getUsername())
				&& userService.existsByUsername(userUpdateDTO.getUsername())) {
			result.rejectValue("username", "error.userDTO", "Username already exists");
			return "edit-profile";
		}

		userService.update(currentUser, userUpdateDTO);
		return "redirect:/profile";
	}

	@GetMapping("/profile/change-password")
	public String showChangePasswordForm(Model model) {
	    model.addAttribute("passwordDTO", new PasswordDTO());
	    return "change-password";
	}

	@PostMapping("/profile/change-password")
	public String processChangePassword(
		@Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO,
		BindingResult result,
		@AuthenticationPrincipal User user) {

		if (result.hasErrors()) {
			return "change-password";
		}

		if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmNewPassword())) {
			result.rejectValue("confirmNewPassword", "error.passwordDTO", "Passwords do not match");
			return "change-password";
		}


		boolean changed = userService.changePassword(user, passwordDTO);
		if (!changed) {
			result.rejectValue("oldPassword", "error.passwordDTO", "Old password is incorrect");
			return "change-password";
		}

		return "redirect:/profile?passwordChanged";
	}

	@GetMapping("/profile/delete")
	public String showDeleteAccountConfirmation() {
	    return "delete-account";
	}

	@PostMapping("/profile/delete")
	public String deleteAccount(@AuthenticationPrincipal User user, HttpServletRequest request, HttpServletResponse response) {
	    userService.delete(user);

	    new SecurityContextLogoutHandler().logout(request, response, null);
	    return "redirect:/";
	}
}
