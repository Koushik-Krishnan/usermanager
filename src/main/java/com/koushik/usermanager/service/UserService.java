package com.koushik.usermanager.service;

import java.util.List;
import java.util.UUID;

import com.koushik.usermanager.domain.CustomField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.usermanager.domain.User;
import com.koushik.usermanager.exceptionHandling.UserNotFoundException;
import com.koushik.usermanager.userrepository.UserRepository;

@Service
@AllArgsConstructor
public class UserService{
	private final UserRepository userRepo;

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

	public User signUpUser(User user){
		boolean userExists = userRepo.findUserByEmail(user.getEmail()).isPresent();

		if(userExists){
			throw new IllegalStateException("email already taken");
		}
		else {
			user.setUserCode(UUID.randomUUID().toString());
			return userRepo.save(user);
		}
	}
}

