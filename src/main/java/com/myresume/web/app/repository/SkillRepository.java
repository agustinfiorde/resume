package com.myresume.web.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.models.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String>{
	
	@Query("SELECT a from Skill a WHERE a.name = :name")
	public Skill findByName(@Param("name") String name);

	@Query("SELECT a from Skill a ORDER BY a.name DESC")
	public Page<Skill> searchAll(Pageable pageable);

	@Query("SELECT a from Skill a WHERE a.removed IS NULL ORDER BY a.name")
	public List<Skill> searchAssets();

	@Query("SELECT a from Skill a WHERE a.name LIKE :q OR a.percent > :q OR a.description LIKE :q ORDER BY a.name DESC")
	public Page<Skill> searchAll(Pageable pageable, @Param("q") String q);
	
//	@Query("SELECT new com.myresume.web.app.models.SkillModel(t.id, t.logo, t.name, t.percent, t.description ) from Skill t WHERE t.id = :id AND t.removed IS NULL")
//	public SkillModel findSolicitudModelById(@Param("id") String id);
	
}
