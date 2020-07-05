package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myresume.web.app.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, String> {

}
