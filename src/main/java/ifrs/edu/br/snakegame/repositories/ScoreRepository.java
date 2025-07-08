package ifrs.edu.br.snakegame.repositories;

import ifrs.edu.br.snakegame.entities.Score;
import ifrs.edu.br.snakegame.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScoreRepository extends JpaRepository<Score, UUID> {
	List<Score> findTop10ByOrderByScoreDescTimeAsc();

	List<Score> findTop10ByUserOrderByScoreDescTimeAsc(User user);
}
