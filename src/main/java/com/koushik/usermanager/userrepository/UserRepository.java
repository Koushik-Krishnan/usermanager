package com.koushik.usermanager.userrepository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koushik.usermanager.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findUserById(Long id);

	Optional<User> findUserByEmail(String email);

	void deleteUserById(Long id);

	@Transactional
	@Modifying
	@Query("UPDATE user a " +
			"SET a.enabled = TRUE WHERE a.email = ?1")
	int enableUser(String email);
	
}
 