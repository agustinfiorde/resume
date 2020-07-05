package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myresume.web.app.entities.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, String>{

}
