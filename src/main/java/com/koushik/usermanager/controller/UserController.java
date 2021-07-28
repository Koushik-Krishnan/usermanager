package com.koushik.usermanager.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koushik.usermanager.domain.User;
import com.koushik.usermanager.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user-management")
public class UserController {
	private final UserService userService;

	/*public UserController(UserService userService) {
		super();
		this.userService = userService;
	}*/
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> user = userService.findAllUser();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getAllUserById(@PathVariable("id")Long id){
		User user = userService.findUserById(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
		}
}
