package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.TechnologyModel;
import com.myresume.web.app.models.entities.Technology;
import com.myresume.web.app.repository.PhotoRepository;
import com.myresume.web.app.repository.TechnologyRepository;

@Component("TechnologyConverter")
public class TechnologyConverter extends Converter<TechnologyModel, Technology> {

	@Autowired
	private TechnologyRepository technologyRepository;

	@Autowired
	private PhotoConverter photoConverter;

	@Autowired
	private PhotoRepository photoRepository;

	public TechnologyModel entityToModel(Technology entity) {
		TechnologyModel model = new TechnologyModel();
		try {

			if (entity.getLogo() != null) {
				model.setLogo(photoConverter.entityToModel(photoRepository.getOne(entity.getLogo().getId())));
			}

			if (entity.getRegistered() != null) {
				model.setRegistered(entity.getRegistered());
			}

			if (entity.getEdited() != null) {
				model.setEdited(entity.getEdited());
			}

			if (entity.getRemoved() != null) {
				model.setRemoved(entity.getRemoved());
			}

			BeanUtils.copyProperties(entity, model);

		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de la Tecnologia", e);
		}

		return model;
	}

	public Technology modelToEntity(TechnologyModel model) {
		Technology entity;

		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = technologyRepository.getOne(model.getId());
			
			if (entity.getLogo() != null && model.getLogo() != null) {
				photoRepository.delete(entity.getLogo());
				entity.setLogo(photoConverter.modelToEntity(model.getLogo()));
			}
			
		} else {
			entity = new Technology();
			entity.setLogo(photoConverter.modelToEntity(model.getLogo()));
		}

		try {

			BeanUtils.copyProperties(model, entity);

			if (model.getRegistered() != null) {
				entity.setRegistered(model.getRegistered());
			}

			if (model.getEdited() != null) {
				entity.setEdited(model.getEdited());
			}

			if (model.getRemoved() != null) {
				entity.setRemoved(model.getRemoved());
			}

		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Tecnologia en entity", e);
		}

		return entity;
	}

	public List<TechnologyModel> entitiesToModels(List<Technology> entities) {
		List<TechnologyModel> models = new ArrayList<>();
		for (Technology a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Technology> modelsToEntities(List<TechnologyModel> m) {
		List<Technology> entities = new ArrayList<>();
		for (TechnologyModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
