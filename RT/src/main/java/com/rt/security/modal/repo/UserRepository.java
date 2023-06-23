package com.rt.security.modal.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rt.security.modal.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);


}
