package com.myresume.web.app.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myresume.web.app.models.CompanyModel;
import com.myresume.web.app.models.entities.Company;
import com.myresume.web.app.repository.CompanyRepository;

@Component("CompanyConverter")
public class CompanyConverter extends Converter<CompanyModel, Company> {

	@Autowired
	private CompanyRepository userRepository;
	

	public CompanyModel entityToModel(Company entity) {
		CompanyModel model = new CompanyModel();
		try {
			BeanUtils.copyProperties(entity, model);

			
		} catch (Exception e) {
			log.error("Error al convertir la entity en el modelo de Comañia", e);
		}

		return model;
	}

	public Company modelToEntity(CompanyModel model) {
		Company entity = new Company();
				
		if (model.getId() != null && !model.getId().isEmpty()) {
			entity = userRepository.getOne(model.getId());
		}
		
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Error al convertir el modelo de Compañia en entity", e);
		}

		return entity;
	}

	public List<CompanyModel> entitiesToModels(List<Company> entities) {
		List<CompanyModel> models = new ArrayList<>();
		for (Company a : entities) {
			models.add(entityToModel(a));
		}
		return models;
	}

	@Override
	public List<Company> modelsToEntities(List<CompanyModel> m) {
		List<Company> entities = new ArrayList<>();
		for (CompanyModel model : m) {
			entities.add(modelToEntity(model));
		}
		return entities;
	}

}
