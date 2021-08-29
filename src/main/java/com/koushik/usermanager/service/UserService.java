package com.koushik.usermanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.koushik.usermanager.registration.token.ConfirmationToken;
import com.koushik.usermanager.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koushik.usermanager.domain.User;
import com.koushik.usermanager.exceptionHandling.UserNotFoundException;
import com.koushik.usermanager.userrepository.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final static String USER_NOT_FOUND_MESSAGE = "user with email %s not found";
	private final UserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;

	/*@Autowired
	public UserService(UserRepository userRepo,BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder ;
	}*/

	/*public User addUser(User user) {
		user.setUserCode(UUID.randomUUID().toString());
		for (CustomField customField : user.getCustomFields()) {
			System.out.println("Name:" + customField.getName());
			System.out.println("Value:" + customField.getValue());
		}
		return userRepo.save(user);
	}*/

	public List<User> findAllUser() {
		return userRepo.findAll();
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUser(Long id) {
		userRepo.deleteUserById(id);
	}

	public User findUserById(Long id) {
		return userRepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
	}

	public User signUpUser(User  user) {
		boolean userExists = userRepo.findUserByEmail(user.getEmail()).isPresent();

		if (userExists) {
			throw new IllegalStateException("email already taken");
		} else {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword.toString());
			System.out.println("encoded password:" + encodedPassword);
			user.setUserCode(UUID.randomUUID().toString());
			userRepo.save(user);

			String token = UUID.randomUUID().toString();
			ConfirmationToken confirmationToken = new ConfirmationToken(
					token,
					LocalDateTime.now(),
					LocalDateTime.now().plusMinutes(15),
					user
			);
			confirmationTokenService.saveConfirmationToken(confirmationToken);
			System.out.println("confirmation token:"+token);
		}
		return user;
	}

		@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepo.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,email)));
	}

	public int enableUser(String email) {
		return userRepo.enableUser(email);
	}
}

