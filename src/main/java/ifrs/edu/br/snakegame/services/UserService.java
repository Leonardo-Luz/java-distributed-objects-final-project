package ifrs.edu.br.snakegame.services;

import ifrs.edu.br.snakegame.dtos.PasswordDTO;
import ifrs.edu.br.snakegame.dtos.UserDTO;
import ifrs.edu.br.snakegame.dtos.UserUpdateDTO;
import ifrs.edu.br.snakegame.entities.User;
import ifrs.edu.br.snakegame.entities.User.Role;
import ifrs.edu.br.snakegame.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User register(UserDTO userDTO) {
		User user = new User();

		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setRole(Role.USER);

		return this.save(user);
	}

	public Long count() {
		return userRepository.count();
	}

	public void update(User currentUser, UserUpdateDTO userUpdateDTO) {
		currentUser.setUsername(userUpdateDTO.getUsername());
		currentUser.setEmail(userUpdateDTO.getEmail());
		userRepository.save(currentUser);
	}

	public boolean changePassword(User user, PasswordDTO passwordDTO) {
		if (!passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
			return false;
		}

		user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
		userRepository.save(user);

		return true;
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findById(UUID id) {
		return userRepository.findById(id);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
