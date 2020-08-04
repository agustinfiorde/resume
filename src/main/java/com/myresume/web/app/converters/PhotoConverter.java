package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.PhotoModel;
import com.myresume.web.app.models.entities.Photo;
import com.myresume.web.app.repository.PhotoRepository;

@Component("PhotoConverter")
public class PhotoConverter extends Converter<PhotoModel, Photo> {

	@Autowired
	private PhotoRepository photoRepository;

	public PhotoModel entityToModel(Photo entity) {
		PhotoModel model = new PhotoModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de la Foto", e);
		}

		return model;
	}

	public Photo modelToEntity(PhotoModel model) {
		Photo entity;
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = photoRepository.getOne(model.getId());
		} else {
			entity = new Photo();
		}

		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Foto en entity", e);
		}

		return entity;
	}

	public List<PhotoModel> entitiesToModels(List<Photo> entities) {
		List<PhotoModel> models = new ArrayList<>();
		for (Photo a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Photo> modelsToEntities(List<PhotoModel> m) {
		List<Photo> entities = new ArrayList<>();
		for (PhotoModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
