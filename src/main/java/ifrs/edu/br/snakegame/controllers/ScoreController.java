package ifrs.edu.br.snakegame.controllers;

import ifrs.edu.br.snakegame.dtos.ScoreDTO;
import ifrs.edu.br.snakegame.entities.Score;
import ifrs.edu.br.snakegame.entities.User;
import ifrs.edu.br.snakegame.services.ScoreService;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scores")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	@GetMapping("/top")
	public String topScores(Model model) {
		model.addAttribute("scores", scoreService.findTop10Global());
		return "top-scores";
	}

	@GetMapping("/top/personal")
	public String topPersonalScores(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("scores", scoreService.findTop10ByUser(user));
		return "personal-scores";
	}

	@GetMapping("/submit")
	public String showSubmitForm(Model model, @AuthenticationPrincipal User user) {
		ScoreDTO scoreDTO = new ScoreDTO();
		scoreDTO.setUserId(user.getId());

		model.addAttribute("scoreDTO", scoreDTO);
		return "submit-score";
	}

	@PostMapping("/submit")
	public String submitScore(
			@Valid @ModelAttribute("scoreDTO") ScoreDTO scoreDTO,
			BindingResult result,
			@AuthenticationPrincipal User user) {
		if (result.hasErrors()) {
			return "submit-score";
		}

		scoreService.register(scoreDTO, user);

		return "redirect:/scores/top";
	}

	@PostMapping("/delete/{id}")
	public String deleteScore(@PathVariable UUID id, @AuthenticationPrincipal User user) {
		Optional<Score> score = scoreService.findById(id);

		if (score.isPresent())
			if (score.get().getUser().equals(user))
				scoreService.delete(score.get());

		return "redirect:/scores/top/personal";
	}
}
