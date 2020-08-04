package com.myresume.web.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myresume.web.app.converters.ProjectConverter;
import com.myresume.web.app.errors.WebException;
import com.myresume.web.app.models.ProjectModel;
import com.myresume.web.app.models.entities.Project;
import com.myresume.web.app.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectConverter projectConverter;

	@Autowired
	private ProjectRepository projectRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Project guardar(ProjectModel model) throws WebException {
		Project project = projectConverter.modelToEntity(model);

		if (project.getRemoved() != null) {
			throw new WebException("El Obejetivo de Desarrollo Sostenible que intenta modificar se encuentra dada de baja.");
		}

		if (project.getName() == null || project.getName().isEmpty()) {
			throw new WebException("El nombre del Project no puede ser vacío.");
		}
		

		return projectRepository.save(project);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { WebException.class, Exception.class })
	public Project eliminar(String id) throws WebException {
		Project project = projectRepository.getOne(id);
		if (project.getRemoved() == null) {
			project.setRemoved(new Date());
			project = projectRepository.save(project);
		} else {
			throw new WebException("El Obejetivo de Desarrollo Sostenible que intenta eliminar ya se encuentra dado de baja.");
		}
		
		return project;
	}

	public Page<Project> listAssets(Pageable paginable, String q) {
		return projectRepository.searchAssets(paginable, "%" + q + "%");
	}

	public Page<Project> listAssets(Pageable paginable) {
		return projectRepository.searchAssets(paginable);
	}
	
	public List<Project> listAssets() {
		return projectRepository.searchAssets();
	}
		
	
}
