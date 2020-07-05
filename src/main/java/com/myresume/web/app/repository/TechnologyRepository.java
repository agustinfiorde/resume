package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myresume.web.app.entities.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, String>{

}
