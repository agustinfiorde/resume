package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.entities.Photo;
import com.myresume.web.app.models.PhotoModel;
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
		Photo photo = new Photo();
		if (model.getId() != null && !model.getId().isEmpty()) {
			photo = photoRepository.getOne(model.getId());
		}

		try {
			BeanUtils.copyProperties(model, photo);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Foto en entity", e);
		}

		return photo;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject entityTOJSON(Photo e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray entitiesTOJSON(List<Photo> e) {
		// TODO Auto-generated method stub
		return null;
	}

}
