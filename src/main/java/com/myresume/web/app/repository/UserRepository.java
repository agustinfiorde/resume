package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	@Query("SELECT a from User a WHERE a.email LIKE :email")
	public User searchByEmail(@Param("email") String email);
	
}
