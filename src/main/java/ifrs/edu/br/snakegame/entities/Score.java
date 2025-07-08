package ifrs.edu.br.snakegame.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private int score;
	private double time;

	@Column(name = "created_at")
	private LocalDate createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDate.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int value) {
		this.score = value;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass())
			return false;

		Score score = (Score) obj;
		return Objects.equals(this.id, score.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public String toString() {
		return "Score: " +
				"id: " + id +
				"\nscore: " + score +
				"\ntime: " + time +
				"\ncreatedAt: " + createdAt +
				"\n" + user;
	}
}
