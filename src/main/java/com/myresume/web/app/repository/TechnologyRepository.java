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
public interface TechnologyRepository extends JpaRepository<Technology, String>{

//	@Query("SELECT a from Technology a, IN(a.territorios) t WHERE a.eliminado IS NULL AND t.id = :id ORDER BY a.nombre")
//	public List<Technology> buscarActivosPorTerritorio(String id);
	
	@Query("SELECT a from Technology a")
	public Page<Technology> searchAssets(Pageable pageable);
	
	@Query("SELECT a from Technology a ORDER BY a.name")
	public List<Technology> searchAssets();

	@Query("SELECT a from Technology a WHERE a.name LIKE :name ")
	public Page<Technology> searchAssets(Pageable pageable,  @Param("name") String name);
	
}
