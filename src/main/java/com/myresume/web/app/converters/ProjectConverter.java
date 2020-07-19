package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.entities.Project;
import com.myresume.web.app.entities.Technology;
import com.myresume.web.app.models.ProjectModel;
import com.myresume.web.app.repository.CompanyRepository;
import com.myresume.web.app.repository.PhotoRepository;
import com.myresume.web.app.repository.ProjectRepository;

@Component("ProjectConverter")
public class ProjectConverter extends Converter<ProjectModel, Project> {

	@Autowired
	private ProjectRepository userRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private PhotoConverter photoConverter;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyConverter companyConverter;
		
	@Autowired
	private TechnologyConverter technologyConverter;
	


	public ProjectModel entityToModel(Project entity) {
		ProjectModel model = new ProjectModel();
		try {
			BeanUtils.copyProperties(entity, model);
			
			if (entity.getPhoto()!=null) {
				model.setPhoto(photoConverter.entityToModel(photoRepository.getOne(entity.getPhoto().getId())));
			}
			
			if (entity.getCompany()!=null) {
				model.setCompany(companyConverter.entityToModel(companyRepository.getOne(entity.getCompany().getId())));
			}
			
			if (entity.getTechnologies()!=null) {
				for (Technology e : entity.getTechnologies()) {
					model.getTechnologies().add(technologyConverter.entityToModel(e));
				}
			}
			
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo del Proyecto", e);
		}

		return model;
	}

	public Project modelToEntity(ProjectModel model) {
		Project entity = new Project();
				
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = userRepository.getOne(model.getId());
		}
		
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo del Proyecto en entity", e);
		}

		return entity;
	}

	public List<ProjectModel> entitiesToModels(List<Project> entities) {
		List<ProjectModel> models = new ArrayList<>();
		for (Project a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Project> modelsToEntities(List<ProjectModel> m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject entityTOJSON(Project e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray entitiesTOJSON(List<Project> e) {
		// TODO Auto-generated method stub
		return null;
	}

}
