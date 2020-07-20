package com.myresume.web.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.entities.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, String> {

	@Query("SELECT a from Technology a ORDER BY a.percent DESC")
	public Page<Technology> searchAll(Pageable pageable);

	@Query("SELECT a from Technology a WHERE a.removed IS NULL ORDER BY a.name")
	public List<Technology> searchAssets();

	@Query("SELECT a from Technology a WHERE a.name LIKE :name OR a.description LIKE :name ORDER BY a.percent DESC")
	public Page<Technology> searchAll(Pageable pageable, @Param("name") String name);
	
//	@Query("SELECT new com.myresume.web.app.models.TechnologyModel(t.id, t.logo, t.name, t.percent, t.description ) from Technology t WHERE t.id = :id AND t.removed IS NULL")
//	public TechnologyModel findSolicitudModelById(@Param("id") String id);

}
