package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.SkillModel;
import com.myresume.web.app.models.entities.Skill;
import com.myresume.web.app.repository.SkillRepository;

@Component("SkillConverter")
public class SkillConverter extends Converter<SkillModel, Skill> {

	@Autowired
	private SkillRepository skillRepository;

	public SkillModel entityToModel(Skill entity) {
		SkillModel model = new SkillModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de la Foto", e);
		}

		return model;
	}

	public Skill modelToEntity(SkillModel model) {
		Skill entity;
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = skillRepository.getOne(model.getId());
		} else {
			entity = new Skill();
		}

		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de la Foto en entity", e);
		}

		return entity;
	}

	public List<SkillModel> entitiesToModels(List<Skill> entities) {
		List<SkillModel> models = new ArrayList<>();
		for (Skill a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Skill> modelsToEntities(List<SkillModel> m) {
		List<Skill> entities = new ArrayList<>();
		for (SkillModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
