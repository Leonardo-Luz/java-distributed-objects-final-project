package ifrs.edu.br.snakegame.services;

import ifrs.edu.br.snakegame.dtos.ScoreDTO;
import ifrs.edu.br.snakegame.entities.Score;
import ifrs.edu.br.snakegame.entities.User;
import ifrs.edu.br.snakegame.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {
	@Autowired
	private ScoreRepository scoreRepository;

	public Score save(Score score) {
		return scoreRepository.save(score);
	}

	public Score register(ScoreDTO scoreDTO, User user) {
		Score score = new Score();

		score.setScore(scoreDTO.getScore());
		score.setTime(scoreDTO.getTime());
		score.setCreatedAt(LocalDate.now());
		score.setUser(user);

		return this.save(score);
	}

	public Long count() {
		return scoreRepository.count();
	}

	public void delete(Score score) {
		scoreRepository.delete(score);
	}

	public Optional<Score> findById(UUID id) {
		return scoreRepository.findById(id);
	}

	public List<Score> findTop10Global() {
		return scoreRepository.findTop10ByOrderByScoreDescTimeAsc();
	}

	public List<Score> findTop10ByUser(User user) {
		return scoreRepository.findTop10ByUserOrderByScoreDescTimeAsc(user);
	}
}
