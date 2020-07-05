package com.myresume.web.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String>{

//	@Query("SELECT a from Project a, IN(a.territorios) t WHERE a.eliminado IS NULL AND t.id = :id ORDER BY a.nombre")
//	public List<Project> buscarActivosPorTerritorio(String id);
	
	@Query("SELECT a from Project a WHERE a.removed IS NULL")
	public Page<Project> searchAssets(Pageable pageable);
	
	@Query("SELECT a from Project a WHERE a.removed IS NULL ORDER BY a.name")
	public List<Project> searchAssets();

	@Query("SELECT a from Project a WHERE a.removed IS NULL AND a.name LIKE :name ")
	public Page<Project> searchAssets(Pageable pageable,  @Param("name") String name);
}
