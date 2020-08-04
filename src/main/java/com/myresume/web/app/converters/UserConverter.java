package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.UserModel;
import com.myresume.web.app.models.entities.User;
import com.myresume.web.app.repository.UserRepository;

@Component("UserConverter")
public class UserConverter extends Converter<UserModel, User> {

	@Autowired
	private UserRepository userRepository;

	public UserModel entityToModel(User entity) {
		UserModel model = new UserModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo del Usuario", e);
		}

		return model;
	}

	public User modelToEntity(UserModel model) {
		User entity;
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = userRepository.getOne(model.getId());
		} else {
			entity = new User();
		}

		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo del Usuario en entity", e);
		}

		return entity;
	}

	public List<UserModel> entitiesToModels(List<User> entities) {
		List<UserModel> models = new ArrayList<>();
		for (User a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<User> modelsToEntities(List<UserModel> m) {
		List<User> entities = new ArrayList<>();
		for (UserModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
