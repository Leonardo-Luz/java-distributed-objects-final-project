package ifrs.edu.br.snakegame.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordDTO {
	@NotBlank(message = "Old Password is required")
	@Size(min = 6, message = "Old Password must be at least 6 characters")
	private String oldPassword;

	@NotBlank(message = "New Password is required")
	@Size(min = 6, message = "New Password must be at least 6 characters")
	private String newPassword;

	@NotBlank(message = "Confirm Password is required")
	@Size(min = 6, message = "Confirm Password must be at least 6 characters")
	private String confirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmPassword) {
		this.confirmNewPassword = confirmPassword;
	}
}
