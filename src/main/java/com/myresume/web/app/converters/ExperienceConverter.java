package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.ExperienceModel;
import com.myresume.web.app.models.entities.Experience;
import com.myresume.web.app.repository.ExperienceRepository;

@Component("ExperienceConverter")
public class ExperienceConverter extends Converter<ExperienceModel, Experience> {

	@Autowired
	private ExperienceRepository technologyRepository;

	
	public ExperienceModel entityToModel(Experience entity) {
		ExperienceModel model = new ExperienceModel();
		try {

			BeanUtils.copyProperties(entity, model);

		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de la Tecnologia", e);
		}

		return model;
	}

	public Experience modelToEntity(ExperienceModel model) {
		Experience entity;

		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = technologyRepository.getOne(model.getId());
		} else {
			entity = new Experience();
		}

		try {

			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Tecnologia en entity", e);
		}

		return entity;
	}

	public List<ExperienceModel> entitiesToModels(List<Experience> entities) {
		List<ExperienceModel> models = new ArrayList<>();
		for (Experience a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Experience> modelsToEntities(List<ExperienceModel> m) {
		List<Experience> entities = new ArrayList<>();
		for (ExperienceModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
