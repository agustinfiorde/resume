package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myresume.web.app.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, String>{

}
