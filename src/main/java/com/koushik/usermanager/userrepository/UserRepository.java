package com.koushik.usermanager.userrepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koushik.usermanager.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findUserById(Long id);

	Optional<User> findUserByEmail(String email);

	void deleteUserById(Long id);
	
	
}
 