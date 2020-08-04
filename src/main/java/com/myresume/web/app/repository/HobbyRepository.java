package com.myresume.web.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.models.entities.Hobby;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, String>{

	@Query("SELECT a from Hobby a ORDER BY a.name DESC")
	public Page<Hobby> searchAll(Pageable pageable);

	@Query("SELECT a from Hobby a WHERE a.removed IS NULL ORDER BY a.name")
	public List<Hobby> searchAssets();

	@Query("SELECT a from Hobby a WHERE a.name LIKE :q OR a.description LIKE :q ORDER BY a.name DESC")
	public Page<Hobby> searchAll(Pageable pageable, @Param("q") String q);
	
//	@Query("SELECT new com.myresume.web.app.models.HobbyModel(t.id, t.logo, t.name, t.percent, t.description ) from Hobby t WHERE t.id = :id AND t.removed IS NULL")
//	public HobbyModel findSolicitudModelById(@Param("id") String id);
	
}
