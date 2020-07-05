package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myresume.web.app.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, String>{

}
