package com.motivationaldiamond.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motivationaldiamond.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
