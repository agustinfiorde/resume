package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.HobbyModel;
import com.myresume.web.app.models.entities.Hobby;
import com.myresume.web.app.repository.HobbyRepository;

@Component("HobbyConverter")
public class HobbyConverter extends Converter<HobbyModel, Hobby> {

	@Autowired
	private HobbyRepository hobbyRepository;

	public HobbyModel entityToModel(Hobby entity) {
		HobbyModel model = new HobbyModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de la Foto", e);
		}

		return model;
	}

	public Hobby modelToEntity(HobbyModel model) {
		Hobby entity;
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = hobbyRepository.getOne(model.getId());
		} else {
			entity = new Hobby();
		}

		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Foto en entity", e);
		}

		return entity;
	}

	public List<HobbyModel> entitiesToModels(List<Hobby> entities) {
		List<HobbyModel> models = new ArrayList<>();
		for (Hobby a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Hobby> modelsToEntities(List<HobbyModel> m) {
		List<Hobby> entities = new ArrayList<>();
		for (HobbyModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
