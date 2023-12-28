package com.rentcars.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentcars.dao.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail (String email);
}