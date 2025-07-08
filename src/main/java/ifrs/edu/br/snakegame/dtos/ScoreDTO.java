package ifrs.edu.br.snakegame.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class ScoreDTO {
	@NotNull(message = "Score is required")
	@Min(value = 0, message = "Score must be positive")
	private Integer score;

	@NotNull(message = "Time is required")
	@Positive
	private double time;

	@NotNull(message = "User ID is required")
	private UUID userId;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
}
