package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.entities.Technology;
import com.myresume.web.app.models.TechnologyModel;
import com.myresume.web.app.repository.TechnologyRepository;

@Component("TechnologyConverter")
public class TechnologyConverter extends Converter<TechnologyModel, Technology> {

	@Autowired
	private TechnologyRepository userRepository;

	public TechnologyModel entityToModel(Technology entity) {
		TechnologyModel model = new TechnologyModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo del Usuario", e);
		}

		return model;
	}

	public Technology modelToEntity(TechnologyModel model) {
		Technology user = new Technology();
		if (model.getId() != null && !model.getId().isEmpty()) {
			user = userRepository.getOne(model.getId());
		}

		try {
			BeanUtils.copyProperties(model, user);
		} catch (Exception e) {
			log.error("Error al convertir el modelo del Usuario en entity", e);
		}

		return user;
	}

	public List<TechnologyModel> entitiesToModels(List<Technology> entities) {
		List<TechnologyModel> model = new ArrayList<>();
		for (Technology a : entities) {
			model.add(entityToModel(a));
		}
		return model;
	}

	@Override
	public List<Technology> modelsToEntities(List<TechnologyModel> m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject entityTOJSON(Technology e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray entitiesTOJSON(List<Technology> e) {
		// TODO Auto-generated method stub
		return null;
	}

}
