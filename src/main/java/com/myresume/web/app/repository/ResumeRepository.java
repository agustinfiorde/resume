package com.myresume.web.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.models.entities.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {

	@Query("SELECT a from Resume a WHERE a.removed IS NULL ORDER BY a.registered ASC")
	public List<Resume> searchAssets();

	@Query("SELECT a from Resume a ORDER BY a.registered ASC")
	public Page<Resume> searchAll(Pageable pageable);

	@Query("SELECT a from Resume a "
			+ "ORDER BY a.registered ASC")
	public Page<Resume> searchAll(Pageable pageable, @Param("q") String q);

}
